package br.com.rodrigo.naoreveze.ui.fragments.datafragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.application.NaoRevezeApplication
import br.com.rodrigo.naoreveze.database.models.User
import br.com.rodrigo.naoreveze.databinding.FragmentUserHeightAndWeightBinding
import br.com.rodrigo.naoreveze.ui.viewmodel.UserViewModel
import br.com.rodrigo.naoreveze.ui.viewmodel.UserViewModelFactory


class UserHeightAndWeightFragment : Fragment() {
    private val binding: FragmentUserHeightAndWeightBinding by lazy {
        FragmentUserHeightAndWeightBinding.inflate(layoutInflater)
    }

    private val userViewModel : UserViewModel by viewModels {
        UserViewModelFactory((requireActivity().application as NaoRevezeApplication).repository)
    }

    private val args: UserHeightAndWeightFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.iconBack.setOnClickListener {
            findNavController().navigate(R.id.action_userHeightAndWeightFragment_to_userNameFragment)
        }


    }

    override fun onStart() {
        super.onStart()

        binding.buttonProximo.setOnClickListener {
            findNavController().navigate(R.id.action_userHeightAndWeightFragment_to_mainActivity)
            val nome = args.nome
            val altura = binding.editTextAltura.text.toString().toFloat()
            val peso = binding.editTextPeso.text.toString().toFloat()
            userViewModel.insertOrUpdateUser(User(1, nome, peso, altura ))
            requireActivity().finish()

        }



    }


}