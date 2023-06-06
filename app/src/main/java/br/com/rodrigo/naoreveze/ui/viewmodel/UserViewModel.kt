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

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()

    var atualizarPesoAlturaJob: Job? = null

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
    fun atualizarPesoAltura(usuarioPeso: Float, usuarioAltura: Float) {
        atualizarPesoAlturaJob = viewModelScope.launch {
            delay(3_000)
            userRepository.atualizarPesoAltura(usuarioPeso, usuarioAltura)
        }
    }


    fun calcularIMC(peso: Float, altura: Float) : Float {
        val alturaMetros = altura / 100
        return peso / (alturaMetros * alturaMetros)
    }

    fun isEntryValid(usuarioPeso: String, usuarioAltura: String): Boolean {
        if (usuarioPeso.isNotBlank() && usuarioAltura.isNotBlank() && usuarioPeso != "0" && usuarioAltura != "0") {
            return true
        }
        return false
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