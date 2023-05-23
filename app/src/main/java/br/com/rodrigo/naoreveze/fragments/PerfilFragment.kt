package br.com.rodrigo.naoreveze.fragments

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.FragmentHomeBinding
import br.com.rodrigo.naoreveze.databinding.FragmentPerfilBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PerfilFragment : Fragment() {
    private val binding: FragmentPerfilBinding by lazy {
        FragmentPerfilBinding.inflate(layoutInflater)
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

        binding.buttonCalcularImc.setOnClickListener {
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

            }
        }


    }
}