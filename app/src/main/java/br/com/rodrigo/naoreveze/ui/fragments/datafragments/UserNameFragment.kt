package br.com.rodrigo.naoreveze.ui.fragments.datafragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.rodrigo.naoreveze.databinding.FragmentUserNameBinding


class UserNameFragment : Fragment() {
    private val binding: FragmentUserNameBinding by lazy {
        FragmentUserNameBinding.inflate(layoutInflater)
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

        binding.buttonProximo.setOnClickListener {
            val nome = binding.editTextNome.text.toString()
            val action =
                UserNameFragmentDirections.actionUserNameFragmentToUserHeightAndWeightFragment(nome)
            findNavController().navigate(action)

        }

    }


}