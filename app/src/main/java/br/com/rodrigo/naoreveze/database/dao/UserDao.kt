package br.com.rodrigo.naoreveze.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.rodrigo.naoreveze.database.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getUser(): List<User>

    @Query("SELECT * FROM user WHERE userId = 1")
    fun getCurrentUser(): LiveData<User?>

    @Query("UPDATE user SET user_weight = :userWeight, user_height = :userHeight WHERE userId = :userId ")
    suspend fun updateWeightHeight(userId: Int = 1, userWeight: Float, userHeight: Float)





}