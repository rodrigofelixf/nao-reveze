package br.com.rodrigo.naoreveze.database.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class User(
    @PrimaryKey
    val userId: Int,
    @ColumnInfo(name = "nome") val nome: String,
    @ColumnInfo(name = "altura") var altura: Float,
    @ColumnInfo(name = "peso") var peso: Float,


)
