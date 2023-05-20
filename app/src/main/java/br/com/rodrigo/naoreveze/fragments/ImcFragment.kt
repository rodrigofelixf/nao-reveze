package br.com.rodrigo.naoreveze.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.rodrigo.naoreveze.R

import br.com.rodrigo.naoreveze.databinding.FragmentImcBinding
import br.com.rodrigo.naoreveze.viewmodel.ImcViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog


class ImcFragment : Fragment() {
    private val binding: FragmentImcBinding by lazy {
        FragmentImcBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy { ViewModelProvider(this).get(ImcViewModel::class.java) }

    private val args: ImcFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatButtonIMC.setOnClickListener {
            findNavController().navigate(R.id.action_imcFragment_to_calculateImcFragment)
        }


    }

    override fun onResume() {
        super.onResume()

        initResultadoImc()


    }


    private fun updateProgressBar() {
        binding.progressBar.progress = viewModel.resultado.value!!.toInt()
    }

    private fun initResultadoImc() {
        viewModel.altura.value = args.altura.toDouble()
        viewModel.peso.value = args.peso.toDouble()
        viewModel.calcularImc()


        viewModel.resultado.observe(this) { resultado ->
            updateProgressBar()
            binding.textViewResultadoImc.text = "%.1f".format(resultado)
            binding.textViewPeso.text = "${args.peso} Kg"
            binding.textViewAltura.text = "${args.altura} Cm"

            val tituloResultado = when {
                resultado < 18.5 -> getString(R.string.peso_abaixo)
                resultado < 25 -> getString(R.string.peso_normal)
                resultado < 30 -> getString(R.string.peso_sobrepeso)
                resultado < 35 -> getString(R.string.peso_grau01)
                resultado < 40 -> getString(R.string.peso_grau02)
                else -> getString(R.string.peso_grau03)
            }

            //  Regras do bottomsheet informacoes
            val infoTextoBottomSheet = when {
                resultado < 18.5 -> getString(R.string.text_info_resultado_peso_abaixo)
                resultado < 25 -> getString(R.string.text_info_resultado_peso_normal)
                resultado < 30 -> getString(R.string.text_info_resultado_peso_sobrepeso)
                resultado < 35 -> getString(R.string.text_info_resultado_peso_obesidade01)
                resultado < 40 -> getString(R.string.text_info_resultado_peso_obesidade02)
                else -> getString(R.string.text_info_resultado_peso_obesidade03)
            }

            // bottomSheet
            binding.imageViewInfo.setOnClickListener {
                val bottomSheetDialog = BottomSheetDialog(requireContext())
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_info_imc)
                val textViewResultado = bottomSheetDialog.findViewById<TextView>(R.id.textView_imc_info)
                textViewResultado!!.text = infoTextoBottomSheet
                bottomSheetDialog.show()
            }

            // validao de usuario que nao fez o calculo ainda
            if (resultado.isNaN()) {
                binding.imageViewInfo.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
                binding.textViewResultadoImc.visibility = View.GONE
                binding.textViewTituloResultadoImc.text = getString(R.string.titulo_resultado_imc)
            } else {
                binding.imageViewInfo.visibility = View.VISIBLE
                binding.progressBar.visibility = View.VISIBLE
                binding.textViewResultadoImc.visibility = View.VISIBLE
                binding.textViewTituloResultadoImc.text = tituloResultado
            }

        }
    }


}