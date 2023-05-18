package br.com.rodrigo.naoreveze.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.rodrigo.naoreveze.R

import br.com.rodrigo.naoreveze.databinding.FragmentImcBinding
import br.com.rodrigo.naoreveze.viewmodel.ImcViewModel


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

        viewModel.altura.value = args.altura.toDouble()
        viewModel.peso.value = args.peso.toDouble()
        viewModel.calcularImc()


        viewModel.resultado.observe(this) { resultado ->
            updateProgressBar()
            binding.textViewResultadoImc.text = "%.1f".format(resultado)

            val tituloResultado = when {
                resultado < 18.5 -> getString(R.string.peso_abaixo)
                resultado < 25 -> getString(R.string.peso_normal)
                resultado < 30 -> getString(R.string.peso_sobrepeso)
                resultado < 35 -> getString(R.string.peso_grau01)
                resultado < 40 -> getString(R.string.peso_grau02)
                else -> getString(R.string.peso_grau03)
            }
            binding.textViewTituloResultado.text = tituloResultado

            val infoTexto = when {
                resultado < 18.5 -> getString(R.string.text_info_resultado_peso_abaixo)
                resultado < 25 -> getString(R.string.peso_normal)
                resultado < 30 -> getString(R.string.peso_sobrepeso)
                resultado < 35 -> getString(R.string.peso_grau01)
                resultado < 40 -> getString(R.string.peso_grau02)
                else -> getString(R.string.peso_grau03)
            }
            binding.textViewInfoImcResultado.text = infoTexto

        }



        // initImcCalculate()
    }


    private fun initImcCalculate() {
        binding.sliderHeight.addOnChangeListener { _, value, _ ->
            val height = value.toInt()
            val heightText = "$height"
            binding.editTextAltura.setText(heightText)
        }

        binding.sliderWeight.addOnChangeListener { _, value, _ ->
            val weight = value.toInt()
            val weightText = "$weight"
            binding.editTextPeso.setText(weightText)
        }

        binding.buttonCalcularImc.setOnClickListener {
            viewModel.altura.value = args.altura.toDouble()
            viewModel.peso.value = args.peso.toDouble()
            viewModel.calcularImc()


            viewModel.resultado.observe(this) { resultado ->
                updateProgressBar()
                binding.textViewResultadoImc.text = "%.1f".format(resultado)

                val tituloResultado = when {
                    resultado < 18.5 -> getString(R.string.peso_abaixo)
                    resultado < 25 -> getString(R.string.peso_normal)
                    resultado < 30 -> getString(R.string.peso_sobrepeso)
                    resultado < 35 -> getString(R.string.peso_grau01)
                    resultado < 40 -> getString(R.string.peso_grau02)
                    else -> getString(R.string.peso_grau03)
                }
                binding.textViewTituloResultado.text = tituloResultado

                val infoTexto = when {
                    resultado < 18.5 -> getString(R.string.text_info_resultado_peso_abaixo)
                    resultado < 25 -> getString(R.string.peso_normal)
                    resultado < 30 -> getString(R.string.peso_sobrepeso)
                    resultado < 35 -> getString(R.string.peso_grau01)
                    resultado < 40 -> getString(R.string.peso_grau02)
                    else -> getString(R.string.peso_grau03)
                }
                binding.textViewInfoImcResultado.text = infoTexto


            }

        }
    }

    private fun updateProgressBar() {
        binding.progressBar.progress = viewModel.resultado.value!!.toInt()
    }


}