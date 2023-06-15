package br.com.rodrigo.naoreveze.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.SearchView

import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.ui.adapter.MusculosAdapter



import br.com.rodrigo.naoreveze.databinding.FragmentTreinoBinding
import br.com.rodrigo.naoreveze.database.model.MusculoModel

import java.text.Normalizer
import java.util.Locale
import kotlin.collections.ArrayList

class TreinoFragment : Fragment() {

    private var listaDeMusculoModels = ArrayList<MusculoModel>()

    private lateinit var musculosAdapter: MusculosAdapter

    private val binding: FragmentTreinoBinding by lazy {
        FragmentTreinoBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewResultadoEncontrado.visibility = View.GONE







        initRecyclerView()
        clickRecyclerView()
        animacaoRightToLeft(binding.recyclerViewMusculos)
        animacaoRightToLeft(binding.searchCardViewTreino)
        addDataToList()
        searchView()




    }

    override fun onResume() {
        super.onResume()


    }
    override fun onDestroyView() {
        super.onDestroyView()

    }

    private fun searchView() {
        binding.searchViewTreino.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                binding.textViewResultadoEncontrado.visibility = View.GONE
                filterList(newText)
                return true
            }
        } )
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<MusculoModel>()
            val normalizedQuery = Normalizer.normalize(query, Normalizer.Form.NFD)
                .replace("[^\\p{ASCII}]".toRegex(), "")
                .lowercase(Locale.ROOT)

            for (i in listaDeMusculoModels) {
                val normalizedTitle = Normalizer.normalize(i.titulo, Normalizer.Form.NFD)
                    .replace("[^\\p{ASCII}]".toRegex(), "")
                    .lowercase(Locale.ROOT)

                if (normalizedTitle.startsWith(normalizedQuery)) {
                    filteredList.add(i)
                }
            }

            musculosAdapter = binding.recyclerViewMusculos.adapter as MusculosAdapter

            if (filteredList.isEmpty()) {
                musculosAdapter.setFilteredList(emptyList())
                binding.textViewResultadoEncontrado.visibility = View.VISIBLE
            } else {
                musculosAdapter.setFilteredList(filteredList.toSet().toList())
            }
        }
    }


    private fun addDataToList() {
        if (listaDeMusculoModels.isEmpty()) {
            listaDeMusculoModels.add(
                MusculoModel(
                    "Peitoral",
                    R.drawable.img_peitoral
                )
            )
            listaDeMusculoModels.add(
                MusculoModel(
                    "Costas",
                    R.drawable.img_costas1
                )
            )
            listaDeMusculoModels.add(
                MusculoModel(
                    "Pernas",
                    R.drawable.img_pernas
                )
            )
            listaDeMusculoModels.add(
                MusculoModel(
                    "Bíceps e Tríceps",
                    R.drawable.img_triceps_biceps
                )
            )
            listaDeMusculoModels.add(
                MusculoModel(
                    "Ombros",
                    R.drawable.img_ombros
                )
            )
            listaDeMusculoModels.add(
                MusculoModel(
                    "Abdômen",
                    R.drawable.img_abdomen4
                )
            )
            listaDeMusculoModels.add(
                MusculoModel(
                    "Aeróbicos",
                    R.drawable.img_aerobico2
                )
            )
        }
    }

    private fun clickRecyclerView() {
        binding.recyclerViewMusculos.adapter = MusculosAdapter(listaDeMusculoModels) { item ->
            when (item) {
                listaDeMusculoModels[0] -> {
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