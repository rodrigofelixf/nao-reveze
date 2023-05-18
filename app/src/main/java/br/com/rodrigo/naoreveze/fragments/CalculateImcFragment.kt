package br.com.rodrigo.naoreveze.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.FragmentCalculateImcBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class CalculateImcFragment : Fragment() {
    private val binding by lazy { FragmentCalculateImcBinding.inflate(layoutInflater) }

    // captura a view do bottomnavigation
    private val bottomNavigationView by lazy {
        requireActivity().findViewById<BottomNavigationView>(
            R.id.bottom_navigation
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // esconde o bottomnavigatiom
        bottomNavigationView.visibility = View.GONE


        binding.iconBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.textMostrarResultado.setOnClickListener {
            if (isValid()) {
                val altura = binding.editTextAltura.text
                val peso = binding.editTextPeso.text
                val action = CalculateImcFragmentDirections.actionCalculateImcFragmentToImcFragment(
                    altura.toString(),
                    peso.toString()
                )
                findNavController().navigate(action)
                bottomNavigationView.visibility = View.VISIBLE
            } else {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT)
                    .show()
            }
        }




    }

    override fun onDestroy() {
        super.onDestroy()
        bottomNavigationView.visibility = View.VISIBLE
    }

    private fun isValid(): Boolean {
        val altura = binding.editTextAltura.text.toString().toDoubleOrNull()
        val peso = binding.editTextPeso.text.toString().toDoubleOrNull()

        return (
                altura != null && peso != null && altura != 0.0 && peso != 0.0)
    }


}

