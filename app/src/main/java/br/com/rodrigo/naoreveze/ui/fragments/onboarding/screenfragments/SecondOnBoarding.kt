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
import br.com.rodrigo.naoreveze.databinding.FragmentSecondOnBoardingBinding
import com.bumptech.glide.Glide

/**
 * Fragmento para exibir o segundo passo do processo de onboarding.
 * Neste fragmento, é exibida uma imagem de fundo e animações fade-in para o título e descrição.
 */
class SecondOnBoarding : Fragment() {

    private val binding: FragmentSecondOnBoardingBinding by lazy {
        FragmentSecondOnBoardingBinding.inflate(layoutInflater)
    }

    /**
     * Método chamado ao criar a visualização do fragmento.
     * Infla o layout do fragmento e retorna a raiz da hierarquia de visualização.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    /**
     * Método chamado quando a visualização do fragmento é criada.
     * Configura os cliques dos botões e carrega a imagem de fundo usando Glide.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)


        // validacao do viewpager2 para ir para proxima tela
        binding.imageNext.setOnClickListener {
            viewPager?.currentItem = 2
        }

        // Carregar e exibir a imagem de fundo usando a biblioteca Glide
        Glide.with(requireContext())
            .load(R.drawable.onboard_screen_2)
            .centerCrop()
            .into(binding.imageViewBackgroundScreen2)
    }

    /**
     * Método chamado quando o fragmento está prestes a se tornar visível.
     * Configura o clique do botão "Pular" e inicia as animações fade-in para o título e descrição.
     */
    override fun onStart() {
        super.onStart()

        binding.textViewPular.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_userNameFragment)
        }

        // Iniciar animações fade-in para o título e descrição
        animacaoFade(binding.tvTitle)
        animacaoFade(binding.tvDescription)
    }

    /**
     * Método para iniciar a animação fade-in em uma view específica.
     * @param view A view alvo da animação.
     */
    private fun animacaoFade(view: View) {
        val fadeIN = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_duration)
        view.startAnimation(fadeIN)
    }
}
