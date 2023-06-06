package br.com.rodrigo.naoreveze.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.application.NaoRevezeApplication
import br.com.rodrigo.naoreveze.databinding.FragmentCalculateImcBinding
import br.com.rodrigo.naoreveze.ui.viewmodel.UserViewModel
import br.com.rodrigo.naoreveze.ui.viewmodel.UserViewModelFactory

import com.google.android.material.bottomnavigation.BottomNavigationView

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
/**
 * Classe responsável por calcular o IMC do usuário e atualizar as informações no banco de dados.
 * Também possui um botão de voltar que cancela a corrotina de cálculo.
 */
class CalculateImcFragment : Fragment() {

    // Binding da classe CalculateImcFragment
    private val binding by lazy { FragmentCalculateImcBinding.inflate(layoutInflater) }

    // Captura a view do BottomNavigationView
    private val bottomNavigationView by lazy {
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
    }

    // Instância do UserViewModel para gerenciar as operações relacionadas aos dados do usuário
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((requireActivity().application as NaoRevezeApplication).repository)
    }

    /**
     * Sobrescrito do Fragment. Chamado quando o layout do fragmento precisa ser inflado.
     * Retorna a raiz do layout inflado.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    /**
     * Sobrescrito do Fragment. Chamado após a criação da visualização do fragmento.
     * Esconde o BottomNavigationView e inicializa o botão de voltar.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigationView.visibility = View.GONE
        initButtonBack()
    }

    /**
     * Sobrescrito do Fragment. Chamado quando o fragmento se torna visível ao usuário.
     * Chama o método addNewUser() para adicionar um ouvinte de clique ao botão de cálculo.
     */
    override fun onStart() {
        super.onStart()

        addNewUser()
    }

    /**
     * Sobrescrito do Fragment. Chamado quando a visualização do fragmento está prestes a ser destruída.
     * Exibe o BottomNavigationView.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        bottomNavigationView.visibility = View.VISIBLE
    }

    /**
     * Navega para a próxima tela exibindo uma animação de carregamento.
     * Desabilita o botão de cálculo durante o processo.
     * O processo de carregamento é simulado usando uma corrotina com um atraso de 3 segundos.
     * @param action A ação de navegação para a próxima tela.
     */
    private fun navigateCalculateAnimation(action: NavDirections) {
        // Mostrar o texto "Calculando"
        binding.buttonCalcularImc.text = getString(R.string.text_calculando)
        // Mostrar a animação de carregamento
        binding.progressBarButton.visibility = View.VISIBLE

        // Desabilitar o botão para evitar cliques repetidos
        binding.buttonCalcularImc.isEnabled = false

        // Simula um processo de carregamento
        lifecycleScope.launch {
            delay(3_000)
            binding.progressBarButton.visibility = View.INVISIBLE
            binding.buttonCalcularImc.text = getString(R.string.text_calcular)
            binding.buttonCalcularImc.isEnabled = true
            bottomNavigationView.visibility = View.VISIBLE
            findNavController().navigate(action)
        }
    }

    /**
     * Adiciona um ouvinte de clique ao botão de cálculo.
     * Verifica se os campos de entrada estão preenchidos corretamente.
     * Se sim, obtém o peso e a altura do usuário a partir dos campos de texto,
     * chama o método navigateCalculateAnimation() e atualiza o peso e a altura do usuário usando o userViewModel.
     * Caso contrário, exibe um Toast informando que todos os campos devem ser preenchidos.
     */
    private fun addNewUser() {
        val action = CalculateImcFragmentDirections.actionCalculateImcFragmentToImcFragment()
        binding.buttonCalcularImc.setOnClickListener {
            if (isEntryValid()) {
                val userWeight = binding.editTextPeso.text.toString().toFloat()
                val userHeight = binding.editTextAltura.text.toString().toFloat()
                navigateCalculateAnimation(action)
                userViewModel.updateWeightHeightJob(userWeight, userHeight)
            } else {
                Toast.makeText(requireContext(), "Erro no calculo, verifique os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Verifica se os campos de entrada de peso e altura são válidos.
     * @return true se os campos de entrada forem válidos, false caso contrário.
     */
    private fun isEntryValid(): Boolean {
        val userWeight = binding.editTextPeso.text.toString().toFloatOrNull()
        val userHeight = binding.editTextAltura.text.toString().toFloatOrNull()

        if (userWeight == null || userWeight <= 0) {
            Toast.makeText(requireContext(), "Dados invalos. Peso e altura devem ser maior que zero.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (userHeight == null || userHeight <= 0) {
            Toast.makeText(requireContext(), "Dados invalos. Peso e altura devem ser maior que zero.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    /**
     * Inicializa o botão de voltar.
     * Se a corrotina de atualização de peso e altura estiver em andamento, o botão de voltar cancela a corrotina.
     * Em seguida, navega para a tela ImcFragment e exibe o BottomNavigationView.
     */
    private fun initButtonBack() {
        binding.iconBack.setOnClickListener {
            // Valida se a corrotina do update peso e altura está em processo.
            // Se estiver, o botão voltar cancela.
            if (userViewModel.updateWeightHeightJob?.isActive == true) {
                userViewModel.updateWeightHeightJob?.cancel()
            }
            findNavController().navigate(R.id.action_calculateImcFragment_to_imcFragment)
            bottomNavigationView.visibility = View.VISIBLE
        }
    }
}
