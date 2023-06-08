package br.com.rodrigo.naoreveze.database.repository

import androidx.lifecycle.LiveData
import br.com.rodrigo.naoreveze.database.dao.UserDao
import br.com.rodrigo.naoreveze.database.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun insertOrUpdateUser(user: User) {
        userDao.insertOrUpdateUser(user)
    }

    suspend fun getUser(): List<User> {
        return userDao.getUser()
    }

    fun getCurrentUser(): LiveData<User?> {
        return userDao.getCurrentUser()
    }

    suspend fun updateWeightHeight(userWeight: Float, userHeight: Float) {
        userDao.updateWeightHeight(userId = 1,userWeight, userHeight )
    }

}
