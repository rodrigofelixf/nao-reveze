package br.com.rodrigo.naoreveze.ui.fragments

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import br.com.rodrigo.naoreveze.application.NaoRevezeApplication


import br.com.rodrigo.naoreveze.databinding.FragmentHomeBinding
import br.com.rodrigo.naoreveze.ui.viewmodel.UserViewModel
import br.com.rodrigo.naoreveze.ui.viewmodel.UserViewModelFactory
import java.util.Locale


class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val userViewModel : UserViewModel by viewModels {
        UserViewModelFactory((requireActivity().application as NaoRevezeApplication).repository)
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





        userViewModel.loadUsers()

        userViewModel.users.observe(viewLifecycleOwner) { users ->
            // Atualize sua interface de usuário com os dados do usuário
            val user = users.firstOrNull()
            user?.let {
                binding.textViewSaudacoesNome.text = it.nome

            }
        }










        initDataAtual()

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun initDataAtual() {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEEE, dd 'de' MMMM", Locale.getDefault())
        val currentDate = dateFormat.format(calendar.time)
        binding.textViewDataAtual.text = currentDate.toString()
    }



}

