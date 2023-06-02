package br.com.rodrigo.naoreveze.ui.fragments.datafragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.FragmentUserHeightAndWeightBinding
import br.com.rodrigo.naoreveze.databinding.FragmentUserNameBinding


class UserHeightAndWeightFragment : Fragment() {
    private val binding: FragmentUserHeightAndWeightBinding by lazy {
        FragmentUserHeightAndWeightBinding.inflate(layoutInflater)
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

        binding.iconBack.setOnClickListener {
            findNavController().navigate(R.id.action_userHeightAndWeightFragment_to_userNameFragment)
        }


        binding.buttonProximo.setOnClickListener {
            findNavController().navigate(R.id.action_userHeightAndWeightFragment_to_mainActivity)

        }

    }


}