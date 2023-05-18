package br.com.rodrigo.naoreveze.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rodrigo.naoreveze.model.ImcModel

class ImcViewModel : ViewModel() {

    var peso: MutableLiveData<Double> = MutableLiveData()
    var altura: MutableLiveData<Double> = MutableLiveData()
    var resultado: MutableLiveData<Double> = MutableLiveData()

    fun calcularImc() {
        val model = ImcModel(peso.value!!, altura.value!!)
        resultado.value = model.calcularImc()
    }

}