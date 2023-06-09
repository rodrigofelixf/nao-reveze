package br.com.rodrigo.naoreveze.ui.fragments.datafragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.FragmentUserNameBinding
import com.bumptech.glide.Glide


/**
 * Fragmento usado para capturar o nome de usuário.
 */
class UserNameFragment : Fragment() {

    // Instância do ViewBinding para o layout do fragmento
    private val binding: FragmentUserNameBinding by lazy {
        FragmentUserNameBinding.inflate(layoutInflater)
    }

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


        // Configura o listener de clique para o botão "Próximo"
        binding.buttonProximo.setOnClickListener { saveNickname() }
    }

    /**
     * Salva o apelido de usuário.
     * Valida o apelido e, se for válido, navega para o fragmento UserHeightAndWeightFragment.
     */
    private fun saveNickname() {
        val nickname = binding.textInputLayoutNickname.editText?.text.toString().trim()


        if (validateNickname(nickname)) {
            // Apelido válido, faça o que for necessário aqui, como salvar no banco de dados
            val action =
                UserNameFragmentDirections.actionUserNameFragmentToUserHeightAndWeightFragment(
                    nickname
                )
            findNavController().navigate(action)
        }
    }

    /**
     * Valida o apelido de usuário.
     *
     * @param nickname Apelido de usuário a ser validado.
     * @return true se o apelido for válido, false caso contrário.
     */
    private fun validateNickname(nickname: String): Boolean {
        if (nickname.isEmpty()) {
            binding.textInputLayoutNickname.error = "O apelido não pode estar vazio"
            return false
        }

        if (nickname.contains(" ")) {
            binding.textInputLayoutNickname.error = "O apelido não pode conter espaços em branco"
            return false
        }

        val specialCharsPattern = Regex("[^a-zA-Z0-9]")
        if (specialCharsPattern.containsMatchIn(nickname)) {
            binding.textInputLayoutNickname.error = "O apelido não pode conter caracteres especiais"
            return false
        }

        if (nickname.length < 3 || nickname.length > 20) {
            binding.textInputLayoutNickname.error = "O apelido deve ter entre 3 e 12 caracteres"
            return false
        }

        binding.textInputLayoutNickname.error = null
        return true
    }
}

