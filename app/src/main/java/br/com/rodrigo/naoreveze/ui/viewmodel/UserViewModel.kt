package br.com.rodrigo.naoreveze.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.rodrigo.naoreveze.database.models.User
import br.com.rodrigo.naoreveze.database.repository.UserRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Classe ViewModel para manipular a lógica de negócio relacionada aos usuários.
 * @property userRepository Repositório para acessar o banco de dados e seus DAOs.
 */
class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    /**
     * LiveData contendo a lista de usuários.
     */
    private val _users = MutableLiveData<List<User>>()

    /**
     * Job utilizado para rastrear a corrotina responsável pela atualização de peso e altura.
     */
    var updateWeightHeightJob: Job? = null

    /**
     * Insere ou atualiza um usuário no banco de dados.
     * @param user O usuário a ser inserido ou atualizado.
     */
    fun insertOrUpdateUser(user: User) {
        viewModelScope.launch {
            userRepository.insertOrUpdateUser(user)
        }
    }

    /**
     * Carrega a lista de usuários do banco de dados.
     */
    fun loadUsers() {
        viewModelScope.launch {
            val users = userRepository.getUser()
            _users.value = users
        }
    }

    /**
     * Obtém o usuário atual.
     * @return LiveData contendo o usuário atual.
     */
    fun getCurrentUser(): LiveData<User?> {
        return userRepository.getCurrentUser()
    }

    /**
     * Executa uma corrotina com um atraso de 3 segundos antes de atualizar o peso e altura do usuário.
     * Se o usuário clicar no botão Voltar durante o atraso, a corrotina será cancelada e a atualização não será feita.
     * @param userWeight O peso do usuário a ser atualizado.
     * @param userHeight A altura do usuário a ser atualizada.
     */
    fun updateWeightHeightJob(userWeight: Float, userHeight: Float) {
        updateWeightHeightJob = viewModelScope.launch {
            delay(3_000)
            userRepository.updateWeightHeight(userWeight, userHeight)
        }
    }

    /**
     * Calcula o IMC (Índice de Massa Corporal) com base no peso e altura fornecidos.
     * @param userWeight O peso do usuário.
     * @param userHeight A altura do usuário.
     * @return O valor do IMC calculado.
     */
    fun calculateBMI(userWeight: Float, userHeight: Float): Float {
        val heightMetersBr = userHeight / 100
        return userWeight / (heightMetersBr * heightMetersBr)
    }

    /**
     * Verifica se as entradas de peso e altura são válidas.
     * @param userWeight O peso do usuário como uma string.
     * @param userHeight A altura do usuário como uma string.
     * @return True se as entradas forem válidas, False caso contrário.
     */
    fun isEntryValid(userWeight: String, userHeight: String): Boolean {
        if (userWeight.isNotBlank() && userHeight.isNotBlank() && userWeight != "0" && userHeight != "0") {
            return true
        }
        return false
    }
}

/**
 * Fábrica para criar instâncias de [UserViewModel].
 * @property userRepository Repositório para acessar o banco de dados e seus DAOs.
 */
class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
