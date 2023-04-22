package br.com.rodrigo.naoreveze.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.MusculoItemBinding
import br.com.rodrigo.naoreveze.model.Musculo

class AdapterMusculos(
    private val context: Context,
    private val listaMusculos: MutableList<Musculo>
) : RecyclerView.Adapter<AdapterMusculos.MusculoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusculoViewHolder {
        val itemLista = MusculoItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MusculoViewHolder(itemLista)
    }


    override fun onBindViewHolder(holder: MusculoViewHolder, position: Int) {
        holder.textTituloMusculo.text = listaMusculos[position].titulo
        listaMusculos[position].image.let { holder.imageMusculo.setImageResource(it) }
        listaMusculos[position].background.let { holder.background.setBackgroundResource(it) }



    }

    override fun getItemCount() = listaMusculos.size


    inner class MusculoViewHolder(binding: MusculoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val textTituloMusculo = binding.textTituloMusculo
        val imageMusculo = binding.imageMusculo
        val background = binding.imageMusculo


    }

    private fun corRandom(): Int {
        val listaGradient = listOf(
            R.drawable.background_gradient,
            R.drawable.background_laranja
        )
        return listaGradient.random()
    }


}