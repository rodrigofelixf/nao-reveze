package br.com.rodrigo.naoreveze.ui.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigo.naoreveze.database.model.MusculoModel
import br.com.rodrigo.naoreveze.databinding.MusculoItemBinding

class MusculosAdapter(
    private var listaMusculoModels: List<MusculoModel>,
    private val onItemClick: (MusculoModel) -> Unit
) : RecyclerView.Adapter<MusculosAdapter.MusculoViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusculoViewHolder {
        val binding = MusculoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusculoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusculoViewHolder, position: Int) {
        val musculo = listaMusculoModels[position]
        holder.bind(musculo, onItemClick = onItemClick)
    }

    override fun getItemCount() = listaMusculoModels.size



    /**
     * Esta função pública adiciona a lista de dados do objeto musculos do recyclerView para ser procurada no searchView.
     * Observe que a listaMusculos esta sendo convertida para toset().toList() para nao haver itens duplicados quando o usuario voltar
     * para tela novamente.
     *
     * @param listaMusculoModels lista de musculos
     *
     */
    fun setFilteredList(listaMusculoModels: List<MusculoModel>) {
        this.listaMusculoModels = listaMusculoModels
        notifyDataSetChanged()
    }

    class MusculoViewHolder(
        private val binding: MusculoItemBinding
    ) : RecyclerView.ViewHolder(binding.root){


        fun bind(musculoModel: MusculoModel, onItemClick: (MusculoModel) -> Unit) {

            binding.textTituloMusculo.text = musculoModel.titulo
            binding.imageMusculo.setImageResource(musculoModel.image)


            binding.root.setOnClickListener { onItemClick(musculoModel) }
        }
    }

}


