package br.com.rodrigo.naoreveze.ui.fragments.onboarding.screenfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.FragmentThirdOnBoardingBinding
import com.bumptech.glide.Glide

/**
 * Classe que representa o fragmento da terceira tela de onboarding.
 *
 * Este fragmento é responsável por exibir a terceira tela de onboarding aos usuários.
 * Ele contém uma imagem de fundo e um botão de ação para começar.
 */
class ThirdOnBoarding : Fragment() {

    // Binding para o layout do fragmento da terceira tela de onboarding
    private val binding: FragmentThirdOnBoardingBinding by lazy {
        FragmentThirdOnBoardingBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtém a referência da ImageView para o fundo da tela
        val imageViewBackground = binding.imageViewBackgroundScreen3

        // Carrega e exibe a imagem de fundo usando Glide
        Glide.with(requireContext())
            .load(R.drawable.onboard_screen_3)
            .centerCrop()
            .into(imageViewBackground)
    }

    /**
     * Método chamado quando o fragmento se torna visível para o usuário.
     *
     * Aqui, configuramos o clique no botão de ação e aplicamos animação de fade no título e na descrição.
     */
    override fun onStart() {
        super.onStart()

        // Configura o clique no botão de ação para navegar para outra tela (por exemplo, "userNameFragment")
        binding.outlineButtonComecar.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_userNameFragment)
        }

        // Aplica uma animação de fade no título e na descrição
        animacaoFade(binding.tvTitle)
        animacaoFade(binding.tvDescription)
    }

    /**
     * Aplica uma animação de fade a uma view.
     *
     * @param view A view na qual a animação será aplicada.
     */
    private fun animacaoFade(view: View) {
        val fadeIN = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_duration)
        view.startAnimation(fadeIN)
    }
}
