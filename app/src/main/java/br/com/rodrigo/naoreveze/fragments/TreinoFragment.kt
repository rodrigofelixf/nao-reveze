package br.com.rodrigo.naoreveze.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.adapter.MusculosAdapter


import br.com.rodrigo.naoreveze.databinding.FragmentTreinoBinding
import br.com.rodrigo.naoreveze.model.Musculo
import java.util.Locale
import kotlin.collections.ArrayList

class TreinoFragment : Fragment() {


    private var _binding: FragmentTreinoBinding? = null
    private val binding get() = _binding!!

    private var listaDeMusculos = ArrayList<Musculo>()

    private lateinit var musculosAdapter: MusculosAdapter

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

        initRecyclerView()
        clickRecyclerView()
        animacaoRightToLeft(binding.recyclerViewMusculos)
        animacaoRightToLeft(binding.treinoCardView)
        addDataToList()


        binding.searchViewTreino.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        } )

    }

    override fun onResume() {
        super.onResume()


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null




    }

    private fun filterList(query : String?) {
        if (query != null) {
            val filteredList = ArrayList<Musculo>()
            for (i in listaDeMusculos ) {
                if (i.titulo.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "Musculo nao encontrado", Toast.LENGTH_SHORT).show()
            } else {
                musculosAdapter = binding.recyclerViewMusculos.adapter as MusculosAdapter
                musculosAdapter.setFilteredList(filteredList.toSet().toList())
            }
        }
    }
    private fun addDataToList() {
        listaDeMusculos.add(
            Musculo(
                "Peitoral",
                R.drawable.img_peitoral
            )
        )
        listaDeMusculos.add(
            Musculo(
                "Costas",
                R.drawable.img_costas1
            )
        )
        listaDeMusculos.add(
            Musculo(
                "Pernas",
                R.drawable.img_pernas
            )
        )
        listaDeMusculos.add(
            Musculo(
                "Bíceps e Tríceps",
                R.drawable.img_triceps_biceps
            )
        )
        listaDeMusculos.add(
            Musculo(
                "Ombros",
                R.drawable.img_ombros
            )
        )
        listaDeMusculos.add(
            Musculo(
                "Abdômen",
                R.drawable.img_abdomen4
            )
        )
        listaDeMusculos.add(
            Musculo(
                "Aeróbicos",
                R.drawable.img_aerobico2
            )
        )

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

    private fun initRecyclerView() {
        binding.recyclerViewMusculos.setHasFixedSize(true)
        binding.recyclerViewMusculos.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun animacaoRightToLeft(view: View) {
        val slideRight = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_right)
        view.startAnimation(slideRight)
    }


}