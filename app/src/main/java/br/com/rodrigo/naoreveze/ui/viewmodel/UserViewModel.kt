package br.com.rodrigo.naoreveze.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rodrigo.naoreveze.database.models.ImcModel

class UserViewModel : ViewModel() {

    var peso: MutableLiveData<Double> = MutableLiveData()
    var altura: MutableLiveData<Double> = MutableLiveData()
    var resultado: MutableLiveData<Double> = MutableLiveData()

    fun calcularImc() {
        val model = ImcModel(peso.value!!, altura.value!!)
        resultado.value = model.calcularImc()
    }

}