package br.com.rodrigo.naoreveze.fragments

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.FragmentHomeBinding
import br.com.rodrigo.naoreveze.databinding.FragmentPerfilBinding

class PerfilFragment : Fragment() {
    private val binding: FragmentPerfilBinding by lazy {
        FragmentPerfilBinding.inflate(layoutInflater)
    }

    private var progr = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateProgressBar()

        binding.buttonIncr.setOnClickListener {
            if (progr <= 90) {
                progr += 10
                updateProgressBar()
            }
        }

        binding.buttonDecr.setOnClickListener {
            if (progr >= 10) {
                progr -= 10
                updateProgressBar()
            }
        }


    }

    private fun updateProgressBar() {
        binding.progressBar.progress = progr
        binding.textViewProgress.text = "$progr%"
    }
}