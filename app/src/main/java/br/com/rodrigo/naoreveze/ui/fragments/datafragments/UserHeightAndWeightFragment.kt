package br.com.rodrigo.naoreveze.ui.fragments.datafragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.application.NaoRevezeApplication
import br.com.rodrigo.naoreveze.database.model.User
import br.com.rodrigo.naoreveze.databinding.FragmentUserHeightAndWeightBinding
import br.com.rodrigo.naoreveze.ui.viewmodel.UserViewModel
import br.com.rodrigo.naoreveze.ui.viewmodel.UserViewModelFactory


/**
 * Fragmento usado para capturar as informações de altura e peso do usuário.
 */
class UserHeightAndWeightFragment : Fragment() {

    // Instância do ViewBinding para o layout do fragmento
    private val binding: FragmentUserHeightAndWeightBinding by lazy {
        FragmentUserHeightAndWeightBinding.inflate(layoutInflater)
    }

    // ViewModel para acessar os dados e a lógica de negócio do usuário
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((requireActivity().application as NaoRevezeApplication).repository)
    }

    // Argumentos recebidos da navegação
    private val args: UserHeightAndWeightFragmentArgs by navArgs()

    /**
     * Cria e retorna a hierarquia de visualização associada ao fragmento.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    /**
     * Chamado imediatamente após a criação da hierarquia de visualização.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura o listener de clique para o ícone de voltar
        binding.iconBack.setOnClickListener {
            findNavController().navigate(R.id.action_userHeightAndWeightFragment_to_userNameFragment)
        }
    }

    /**
     * Chamado quando o fragmento está visível para o usuário e em execução.
     * Adiciona um novo registro de usuário.
     */
    override fun onStart() {
        super.onStart()
        addNewUser()
    }

    /**
     * Verifica se a entrada do usuário é válida.
     *
     * @return true se a entrada for válida, false caso contrário.
     */
    private fun isEntryValid(): Boolean {
        return userViewModel.isEntryValid(
            binding.editTextPeso.text.toString(),
            binding.editTextAltura.text.toString()
        )
    }

    /**
     * Adiciona um novo usuário com informações de altura e peso.
     * Navega para o splashScreen após a inserção bem-sucedida e finaliza a activity pai.
     */
    private fun addNewUser() {
        binding.buttonProximo.setOnClickListener {
            if (isEntryValid()) {
                val userName = args.nome
                val userHeight = binding.editTextAltura.text.toString().toFloat()
                val userWeight = binding.editTextPeso.text.toString().toFloat()
                userViewModel.insertOrUpdateUser(User(1, userName, userHeight, userWeight))
                findNavController().navigate(R.id.action_userHeightAndWeightFragment_to_splashActivity)
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
