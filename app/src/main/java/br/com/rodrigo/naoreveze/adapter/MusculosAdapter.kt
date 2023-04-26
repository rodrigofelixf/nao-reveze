package br.com.rodrigo.naoreveze.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigo.naoreveze.databinding.MusculoItemBinding
import br.com.rodrigo.naoreveze.model.Musculo

class MusculosAdapter(
    private var listaMusculos: List<Musculo>,
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



    /**
     * Esta função pública adiciona a lista de dados do objeto musculos do recyclerView para ser procurada no searchView.
     * Observe que a listaMusculos esta sendo convertida para toset().toList() para nao haver itens duplicados quando o usuario voltar
     * para tela novamente.
     *
     * @param listaMusculos lista de musculos
     *
     */
    fun setFilteredList(listaMusculos: List<Musculo>) {
        this.listaMusculos = listaMusculos
        notifyDataSetChanged()
    }

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


