package br.com.rodrigo.naoreveze.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.application.NaoRevezeApplication
import br.com.rodrigo.naoreveze.databinding.FragmentCalculateImcBinding
import br.com.rodrigo.naoreveze.ui.viewmodel.UserViewModel
import br.com.rodrigo.naoreveze.ui.viewmodel.UserViewModelFactory

import com.google.android.material.bottomnavigation.BottomNavigationView

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

    private val userViewModel : UserViewModel by viewModels {
        UserViewModelFactory((requireActivity().application as NaoRevezeApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bottomNavigationView.visibility = View.GONE
        initButtonBack()


    }

    override fun onStart() {
        super.onStart()

        addNewUser()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bottomNavigationView.visibility = View.VISIBLE
    }


    private fun navigateCalculateAnimation(action: NavDirections) {
        // Mostrar o texto "Calculando"
        binding.buttonCalcularImc.text = getString(R.string.text_calculando)
        // Mostrar a animação de carregamento
        binding.progressBarButton.visibility = View.VISIBLE

        // Desabilitar o botão para evitar cliques repetidos
        binding.buttonCalcularImc.isEnabled = false

        // Simula um processo de carregamento
        lifecycleScope.launch {
            delay(3_000)
            binding.progressBarButton.visibility = View.INVISIBLE
            binding.buttonCalcularImc.text = getString(R.string.text_calcular)
            binding.buttonCalcularImc.isEnabled = true
            bottomNavigationView.visibility = View.VISIBLE
            findNavController().navigate(action)
        }
    }

    private fun addNewUser() {
        val action = CalculateImcFragmentDirections.actionCalculateImcFragmentToImcFragment()
        binding.buttonCalcularImc.setOnClickListener {
            if (isEntryValid()) {
                val usuarioPeso = binding.editTextPeso.text.toString().toFloat()
                val usuarioAltura = binding.editTextAltura.text.toString().toFloat()
                navigateCalculateAnimation(action)
                userViewModel.atualizarPesoAltura(usuarioPeso, usuarioAltura)
            } else {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isEntryValid(): Boolean {
        return userViewModel.isEntryValid(
            binding.editTextPeso.text.toString(),
            binding.editTextAltura.text.toString()
        )
    }

    private fun initButtonBack(){
        binding.iconBack.setOnClickListener {
            //valida se a coroutina do update peso e altura esta em processo. se estiver o botao voltar cancela
            if (userViewModel.atualizarPesoAlturaJob?.isActive == true) {
                userViewModel.atualizarPesoAlturaJob?.cancel()
            }
            findNavController().navigate(R.id.action_calculateImcFragment_to_imcFragment)
            bottomNavigationView.visibility = View.VISIBLE
        }
    }




}

