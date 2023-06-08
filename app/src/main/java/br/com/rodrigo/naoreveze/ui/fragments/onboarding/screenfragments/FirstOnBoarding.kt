package br.com.rodrigo.naoreveze.ui.fragments.onboarding.screenfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.FragmentFirstOnBoardingBinding
import com.bumptech.glide.Glide

/**
 * Classe que representa o fragmento da primeira tela de onboarding.
 *
 * Este fragmento é responsável por exibir a primeira tela de onboarding aos usuários.
 * Ele contém uma imagem de fundo, um título, uma descrição e um botão de navegação para a próxima tela.
 */
class FirstOnBoarding : Fragment() {

    // Binding para o layout do fragmento da primeira tela de onboarding
    private val binding: FragmentFirstOnBoardingBinding by lazy {
        FragmentFirstOnBoardingBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtém a referência do ViewPager2 da atividade
        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)

        // Configura o clique no botão de próxima tela para navegar para a próxima tela no ViewPager2
        binding.imageNext.setOnClickListener {
            viewPager?.currentItem = 1
        }

        // Obtém a referência da ImageView para o fundo da tela
        val imageViewBackground = binding.imageViewBackgroundScreen1

        // Carrega e exibe a imagem de fundo usando Glide
        Glide.with(requireContext())
            .load(R.drawable.onboard_screen_1)
            .centerCrop()
            .into(imageViewBackground)

        // Configura o clique no texto "Pular" para navegar para outra tela (por exemplo, "userNameFragment")
        binding.textViewPular.setOnClickListener {
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
