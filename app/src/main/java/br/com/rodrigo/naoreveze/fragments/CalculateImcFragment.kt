package br.com.rodrigo.naoreveze.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.FragmentCalculateImcBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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
    ): View {
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // esconde o bottomnavigatiom
        bottomNavigationView.visibility = View.GONE


        binding.iconBack.setOnClickListener {
            findNavController().navigate(R.id.action_calculateImcFragment_to_imcFragment)
            bottomNavigationView.visibility = View.VISIBLE
        }

        binding.buttonCalcularImc.setOnClickListener {
            if (isValid()) {
                val altura = binding.editTextAltura.text
                val peso = binding.editTextPeso.text
                val action = CalculateImcFragmentDirections.actionCalculateImcFragmentToImcFragment(
                    altura.toString(),
                    peso.toString()
                )
                navigateCalculateAnimation(action)
            } else {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun isValid(): Boolean {
        val altura = binding.editTextAltura.text.toString().toDoubleOrNull()
        val peso = binding.editTextPeso.text.toString().toDoubleOrNull()

        return (
                altura != null && peso != null && altura != 0.0 && peso != 0.0)
    }

    private fun showSplashScreen(action: NavDirections) {


        // Exibir o splash screen
        findNavController().navigate(R.id.action_calculateImcFragment_to_splashFragment)

        // Aguardar um período de tempo e, em seguida, navegar para outra tela
        lifecycleScope.launch {
            delay(3_000)
            findNavController().navigate(action)
        }
    }

    private fun navigateCalculateAnimation(action: NavDirections) {
        // Mostrar o texto "Calculando"
        binding.buttonCalcularImc.text = getString(R.string.text_calculando)
        // Mostrar a animação de carregamento
        binding.progressBarButton.visibility = View.VISIBLE

        // Desabilitar o botão para evitar cliques repetidos
        binding.buttonCalcularImc.isEnabled = false


        // Simular um processo de carregamento
        lifecycleScope.launch {
            delay(3_000)
            binding.progressBarButton.visibility = View.INVISIBLE
            binding.buttonCalcularImc.text = getString(R.string.text_calcular)
            binding.buttonCalcularImc.isEnabled = true
            bottomNavigationView.visibility = View.VISIBLE
            findNavController().navigate(action)
        }
    }




}

