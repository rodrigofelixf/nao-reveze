package br.com.rodrigo.naoreveze.ui.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.application.NaoRevezeApplication

import br.com.rodrigo.naoreveze.databinding.FragmentImcBinding
import br.com.rodrigo.naoreveze.ui.MainActivity
import br.com.rodrigo.naoreveze.ui.viewmodel.UserViewModel
import br.com.rodrigo.naoreveze.ui.viewmodel.UserViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * Fragmento responsável por exibir as informações do IMC (Índice de Massa Corporal)
 * persistidas no banco de dados. Possui botões que redirecionam para a classe CalculateImcFragment
 * para atualização dos cálculos.
 */
class ImcFragment : Fragment() {
    private val binding: FragmentImcBinding by lazy {
        FragmentImcBinding.inflate(layoutInflater)
    }
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((requireActivity().application as NaoRevezeApplication).repository)
    }

    private var resultBMI = 0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observa as alterações no usuário e atualiza as saudações
        userViewModel.getCurrentUser().observe(viewLifecycleOwner) { user ->
            val greetings = "Olá, ${user?.userName}"
            binding.textViewSaudacoesNome.text = greetings
        }

    }

    override fun onStart() {
        super.onStart()

        // Inicializa o resultado do IMC e configura a navegação para a classe CalculateImcFragment
        initResultImc()
        setupImcCalculateScreenNavigation()

    }

    /**
     * Atualiza a barra de progresso com o resultado do IMC.
     */
    private fun updateProgressBar() {
        binding.progressBar.progress = resultBMI.toInt()
    }

    /**
     * Inicializa o resultado do IMC e atualiza as informações na tela.
     */
    private fun initResultImc() {
        userViewModel.getCurrentUser().observe(viewLifecycleOwner) { user ->
            val userWeight = user!!.userWeight
            val userHeight = user.userHeight
            resultBMI = userViewModel.calculateBMI(userWeight, userHeight)

            updateProgressBar()


            // Define o título do resultado do IMC com base no valor calculado
            val resultTitle = when {
                resultBMI < 18.5 -> getString(R.string.peso_abaixo)
                resultBMI < 25 -> getString(R.string.peso_normal)
                resultBMI < 30 -> getString(R.string.peso_sobrepeso)
                resultBMI < 35 -> getString(R.string.peso_grau01)
                resultBMI < 40 -> getString(R.string.peso_grau02)
                else -> getString(R.string.peso_grau03)
            }
            binding.textViewTituloTesultadoImc.text = resultTitle

            // Define o texto de informações para o BottomSheet com base no valor calculado
            val infoTextBottomSheet = when {
                resultBMI < 18.5 -> getString(R.string.text_info_resultado_peso_abaixo)
                resultBMI < 25 -> getString(R.string.text_info_resultado_peso_normal)
                resultBMI < 30 -> getString(R.string.text_info_resultado_peso_sobrepeso)
                resultBMI < 35 -> getString(R.string.text_info_resultado_peso_obesidade01)
                resultBMI < 40 -> getString(R.string.text_info_resultado_peso_obesidade02)
                else -> getString(R.string.text_info_resultado_peso_obesidade03)
            }
            binding.imageViewInfo.setOnClickListener {
                // Exibe um BottomSheet com as informações do IMC
                BottomSheetDialog(requireContext()).apply {
                    setContentView(R.layout.bottom_sheet_info_imc)
                    findViewById<TextView>(R.id.textView_imc_info)?.text = infoTextBottomSheet
                    show()
                }
            }

            // Atualiza o texto de resultado, peso e altura na tela
            updateImcResult(resultBMI)
            binding.textViewPeso.text = "%.1f".format(userWeight) + " Kg"
            binding.textViewAltura.text = "%.0f".format(userHeight) + " Cm"

            // Aplica o estilo de fundo na tabela do resultado do IMC com base no valor calculado
            when {
                resultBMI < 18.5 -> binding.tableResultAbaixoPeso.setBackgroundResource(R.drawable.background_table)
                resultBMI < 25 -> binding.tableResultNormal.setBackgroundResource(R.drawable.background_table)
                resultBMI < 30 -> binding.tableResultSobrepesoPeso.setBackgroundResource(R.drawable.background_table)
                resultBMI < 35 -> binding.tableResultObesidade1Peso.setBackgroundResource(R.drawable.background_table)
                resultBMI < 40 -> binding.tableResultObesidade2Peso.setBackgroundResource(R.drawable.background_table)
                resultBMI > 39.9 -> binding.tableResultObesidade3Peso.setBackgroundResource(R.drawable.background_table)
            }
        }
    }

    /**
     * Configura a navegação para a classe CalculateImcFragment ao clicar nos botões relacionados.
     */
    private fun setupImcCalculateScreenNavigation() {
        val navigateToCalculateFragment = {
            findNavController().navigate(R.id.action_imcFragment_to_calculateImcFragment)
        }

        binding.cardViewResultadoPeso.setOnClickListener { navigateToCalculateFragment() }
        binding.cardViewResultadoAltura.setOnClickListener { navigateToCalculateFragment() }
        binding.buttonTelaCalcular.setOnClickListener { navigateToCalculateFragment() }
    }

    /**
     * Atualiza o resultado do IMC e exibe-o na tela.
     * Se o resultado for menor ou igual a 39.9, exibe o valor formatado com uma casa decimal.
     * Caso contrário, exibe o texto "40+".
     */
    private fun updateImcResult(resultBMI: Float) {
        if (resultBMI <= 39.9) {
            binding.textViewResultadoImc.text = "%.1f".format(resultBMI)
        } else {
            binding.textViewResultadoImc.text = getString(R.string.text_mais_de_40)
        }
    }
}
