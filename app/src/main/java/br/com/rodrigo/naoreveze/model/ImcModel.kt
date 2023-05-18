package br.com.rodrigo.naoreveze.model

import androidx.lifecycle.MutableLiveData

class ImcModel(val peso: Double, val altura: Double) {

    fun calcularImc(): Double {
        val alturaMetros = altura / 100
        return peso / (alturaMetros * alturaMetros)

    }

}