package br.com.rodrigo.naoreveze.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.MusculoItemBinding
import br.com.rodrigo.naoreveze.model.Musculo

class MusculosAdapter(
    private val listaMusculos: List<Musculo>,
    private val onItemClick: (Musculo) -> Unit
) : RecyclerView.Adapter<MusculosAdapter.MusculoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusculoViewHolder {
        val binding = MusculoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusculoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusculoViewHolder, position: Int) {
        val musculo = listaMusculos[position]
        holder.bind(musculo, onItemClick = onItemClick)
    }

    override fun getItemCount() = listaMusculos.size

    class MusculoViewHolder(
        private val binding: MusculoItemBinding
    ) : RecyclerView.ViewHolder(binding.root){


        fun bind(musculo: Musculo, onItemClick: (Musculo) -> Unit) {

                binding.textTituloMusculo.text = musculo.titulo
                binding.imageMusculo.setImageResource(musculo.image)
                binding.imageMusculo.setBackgroundResource(musculo.background)

            binding.root.setOnClickListener { onItemClick(musculo) }
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


