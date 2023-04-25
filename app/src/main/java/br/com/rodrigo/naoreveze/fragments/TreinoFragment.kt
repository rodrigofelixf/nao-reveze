package br.com.rodrigo.naoreveze.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.adapter.MusculosAdapter


import br.com.rodrigo.naoreveze.databinding.FragmentTreinoBinding
import br.com.rodrigo.naoreveze.model.Musculo

class TreinoFragment : Fragment() {


    private var _binding: FragmentTreinoBinding? = null
    private val binding get() = _binding!!

    private val listaDeMusculos = listOf(
        Musculo(
            "Peitoral",
            R.drawable.img_peitoral,
            R.drawable.background_laranja
        ),
        Musculo(
            "Costas Largas",
            R.drawable.img_peitoral,
            R.drawable.background_gradient
        )
    )


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


        clickRecyclerView()
        binding.recyclerViewMusculos.layoutManager = LinearLayoutManager(requireContext())


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clickRecyclerView() {
        binding.recyclerViewMusculos.adapter = MusculosAdapter(listaDeMusculos) { item ->
            when (item) {
                listaDeMusculos[0] -> {
                    findNavController().navigate(R.id.action_treinoFragment_to_peitoralFragment)
                }
                else -> {

                }
            }
        }
    }


}