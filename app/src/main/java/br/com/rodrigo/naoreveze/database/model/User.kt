package br.com.rodrigo.naoreveze.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val userId: Int,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "user_height") var userHeight: Float,
    @ColumnInfo(name = "user_weight") var userWeight: Float,


    )
