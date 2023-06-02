package br.com.rodrigo.naoreveze.database.models

class ImcModel(val peso: Double, val altura: Double) {

    fun calcularImc(): Double {
        val alturaMetros = altura / 100
        return peso / (alturaMetros * alturaMetros)

    }

}