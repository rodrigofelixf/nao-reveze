package br.com.rodrigo.naoreveze.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope

import br.com.rodrigo.naoreveze.database.models.ImcModel
import br.com.rodrigo.naoreveze.database.models.User
import br.com.rodrigo.naoreveze.database.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    var peso: MutableLiveData<Double> = MutableLiveData()
    var altura: MutableLiveData<Double> = MutableLiveData()
    var resultado: MutableLiveData<Double> = MutableLiveData()

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    fun insertOrUpdateUser(user: User) {
        viewModelScope.launch {
            userRepository.insertOrUpdateUser(user)
        }
    }

    fun loadUsers() {
        viewModelScope.launch {
            val users = userRepository.getUser()
            _users.value = users
        }
    }

    fun obterUsuario(): LiveData<User?> {
        return userRepository.obterUsuario()
    }






    fun calcularImc() {
        val model = ImcModel(peso.value!!, altura.value!!)
        resultado.value = model.calcularImc()
    }


}

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}