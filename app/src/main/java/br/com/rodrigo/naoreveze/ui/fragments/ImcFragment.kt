package br.com.rodrigo.naoreveze.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.application.NaoRevezeApplication

import br.com.rodrigo.naoreveze.databinding.FragmentImcBinding
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

    private var resultado = 0f

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
        userViewModel.obterUsuario().observe(viewLifecycleOwner) { usuario ->
            val saudacoes = "Olá, ${usuario?.nome}"
            binding.textViewSaudacoesNome.text = saudacoes
        }
    }

    override fun onStart() {
        super.onStart()

        // Inicializa o resultado do IMC e configura a navegação para a classe CalculateImcFragment
        initResultadoImc()
        setupImcCalculateScreenNavigation()

    }

    /**
     * Atualiza a barra de progresso com o resultado do IMC.
     */
    private fun updateProgressBar() {
        binding.progressBar.progress = resultado.toInt()
    }

    /**
     * Inicializa o resultado do IMC e atualiza as informações na tela.
     */
    private fun initResultadoImc() {
        userViewModel.obterUsuario().observe(viewLifecycleOwner) { usuario ->
            val userPeso = usuario!!.peso
            val userAltura = usuario.altura
            resultado = userViewModel.calcularIMC(userPeso, userAltura)

            updateProgressBar()


            // Define o título do resultado do IMC com base no valor calculado
            val tituloResultado = when {
                resultado < 18.5 -> getString(R.string.peso_abaixo)
                resultado < 25 -> getString(R.string.peso_normal)
                resultado < 30 -> getString(R.string.peso_sobrepeso)
                resultado < 35 -> getString(R.string.peso_grau01)
                resultado < 40 -> getString(R.string.peso_grau02)
                else -> getString(R.string.peso_grau03)
            }
            binding.textViewTituloTesultadoImc.text = tituloResultado

            // Define o texto de informações para o BottomSheet com base no valor calculado
            val infoTextoBottomSheet = when {
                resultado < 18.5 -> getString(R.string.text_info_resultado_peso_abaixo)
                resultado < 25 -> getString(R.string.text_info_resultado_peso_normal)
                resultado < 30 -> getString(R.string.text_info_resultado_peso_sobrepeso)
                resultado < 35 -> getString(R.string.text_info_resultado_peso_obesidade01)
                resultado < 40 -> getString(R.string.text_info_resultado_peso_obesidade02)
                else -> getString(R.string.text_info_resultado_peso_obesidade03)
            }
            binding.imageViewInfo.setOnClickListener {
                // Exibe um BottomSheet com as informações do IMC
                BottomSheetDialog(requireContext()).apply {
                    setContentView(R.layout.bottom_sheet_info_imc)
                    findViewById<TextView>(R.id.textView_imc_info)?.text = infoTextoBottomSheet
                    show()
                }
            }

            // Atualiza o texto de resultado, peso e altura na tela
            updateImcResult(resultado)
            binding.textViewPeso.text = "%.0f".format(userPeso) + " Kg"
            binding.textViewAltura.text = "%.0f".format(userAltura) + " Cm"

            // Aplica o estilo de fundo na tabela do resultado do IMC com base no valor calculado
            when {
                resultado < 18.5 -> binding.tableResultAbaixoPeso.setBackgroundResource(R.drawable.background_table)
                resultado < 25 -> binding.tableResultNormal.setBackgroundResource(R.drawable.background_table)
                resultado < 30 -> binding.tableResultSobrepesoPeso.setBackgroundResource(R.drawable.background_table)
                resultado < 35 -> binding.tableResultObesidade1Peso.setBackgroundResource(R.drawable.background_table)
                resultado < 40 -> binding.tableResultObesidade2Peso.setBackgroundResource(R.drawable.background_table)
                resultado > 39.9 -> binding.tableResultObesidade3Peso.setBackgroundResource(R.drawable.background_table)
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
    private fun updateImcResult(resultado: Float) {
        if (resultado <= 39.9) {
            binding.textViewResultadoImc.text = "%.1f".format(resultado)
        } else {
            binding.textViewResultadoImc.text = getString(R.string.text_mais_de_40)
        }
    }
}
