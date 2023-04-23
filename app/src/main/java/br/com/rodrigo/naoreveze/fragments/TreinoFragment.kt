package br.com.rodrigo.naoreveze.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.adapter.MusculosAdapter


import br.com.rodrigo.naoreveze.databinding.FragmentTreinoBinding
import br.com.rodrigo.naoreveze.model.Musculo

class TreinoFragment : Fragment() {

    private var _binding: FragmentTreinoBinding? = null
    private val binding get() = _binding!!



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


        val list = listOf(
            Musculo(
                "Peitoral",
                R.drawable.img_peitoral,
                R.drawable.background_laranja),
            Musculo(
                "Costas Largas",
                R.drawable.img_peitoral,
                R.drawable.background_gradient)

        )

        binding.recyclerViewMusculos.adapter = MusculosAdapter(list) { item ->
            when (item) {
                list[0] -> {
                    openFragment(HomeFragment())
                }
                else -> {

                }
            }
        }

        binding.recyclerViewMusculos.layoutManager = LinearLayoutManager(requireContext())


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

        val musculoPeitoral2 = Musculo(
            "Costas Largas",
            R.drawable.img_peitoral,
            R.drawable.background_gradient
        )

    }

    private fun openFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.slide_in, R.anim.slide_out)
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}