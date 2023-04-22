package br.com.rodrigo.naoreveze.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.adapter.AdapterMusculos

import br.com.rodrigo.naoreveze.databinding.FragmentTreinoBinding
import br.com.rodrigo.naoreveze.model.Musculo

class TreinoFragment : Fragment() {

    private var _binding: FragmentTreinoBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterMusculos: AdapterMusculos
    private val listaMusculos: MutableList<Musculo> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTreinoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewMusculos = binding.recyclerViewMusculos
        recyclerViewMusculos.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewMusculos.setHasFixedSize(true)
        adapterMusculos = AdapterMusculos(requireContext(), listaMusculos)
        recyclerViewMusculos.adapter = adapterMusculos
        itensDeLista()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun itensDeLista() {
        val musculoPeitoral = Musculo(
            "Peitoral",
            R.drawable.img_peitoral,
            R.drawable.background_laranja
        )
        listaMusculos.add(musculoPeitoral)

        val musculoPeitoral2 = Musculo(
            "Costas Largas",
            R.drawable.img_peitoral,
            R.drawable.background_gradient
        )
        listaMusculos.add(musculoPeitoral2)


    }


}