package br.com.rodrigo.naoreveze.database.repository

import androidx.lifecycle.LiveData
import br.com.rodrigo.naoreveze.database.dao.UserDao
import br.com.rodrigo.naoreveze.database.models.User

class UserRepository(private val userDao: UserDao) {
    suspend fun insertOrUpdateUser(user: User) {
        userDao.insertOrUpdateUser(user)
    }

    suspend fun getUser(): List<User> {
        return userDao.getUser()
    }

    fun obterUsuario(): LiveData<User?> {
        return userDao.obterUsuario()
    }

    suspend fun atualizarPesoAltura(usuarioPeso: Float, usuarioAltura: Float) {
        userDao.atualizaPesoAltura(userId = 1,usuarioPeso, usuarioAltura )
    }

}
