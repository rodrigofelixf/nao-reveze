package br.com.rodrigo.naoreveze.application

import android.app.Application


import br.com.rodrigo.naoreveze.database.UserDatabase

import br.com.rodrigo.naoreveze.database.repository.UserRepository



class NaoRevezeApplication : Application() {

    val database by lazy { UserDatabase.getInstance(this) }
    val repository by lazy { UserRepository(database.userDao()) }
}



