package br.com.rodrigo.naoreveze.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.MusculoItemBinding
import br.com.rodrigo.naoreveze.fragments.HomeFragment
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
        val musculo = listaMusculos[position]
        holder.bind(musculo)
    }

    override fun getItemCount() = listaMusculos.size

    inner class MusculoViewHolder(
        private val binding: MusculoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(musculo: Musculo) {
            binding.apply {
                textTituloMusculo.text = musculo.titulo
                imageMusculo.setImageResource(musculo.image)
                imageMusculo.setBackgroundResource(musculo.background)
            }
        }
    }

}


private fun corRandom(): Int {
    val listaGradient = listOf(
        R.drawable.background_gradient,
        R.drawable.background_laranja
    )
    return listaGradient.random()


}


