package br.com.rodrigo.naoreveze.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.rodrigo.naoreveze.database.models.User
import com.google.android.material.circularreveal.CircularRevealHelper.Strategy

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(user: User)

    @Query("SELECT * FROM usuario")
    suspend fun getUser(): List<User>

    @Query("SELECT * FROM usuario WHERE userId = 1")
    fun obterUsuario(): LiveData<User?>

}