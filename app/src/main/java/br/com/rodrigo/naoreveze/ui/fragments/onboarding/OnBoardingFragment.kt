package br.com.rodrigo.naoreveze.ui.fragments.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.rodrigo.naoreveze.databinding.FragmentOnBoardingBinding
import br.com.rodrigo.naoreveze.ui.adapter.ViewPagerAdapter
import br.com.rodrigo.naoreveze.ui.fragments.onboarding.screenfragments.FirstOnBoarding
import br.com.rodrigo.naoreveze.ui.fragments.onboarding.screenfragments.SecondOnBoarding
import br.com.rodrigo.naoreveze.ui.fragments.onboarding.screenfragments.ThirdOnBoarding

/**
 * Classe que representa o fragmento de onboarding.
 *
 * Este fragmento é responsável por exibir um processo de introdução ou tutorial para os usuários.
 * Ele utiliza um ViewPager para exibir diferentes telas de onboarding em uma sequência.
 */
class OnBoardingFragment : Fragment() {

    // Binding para o layout do fragmento de onboarding
    private val binding: FragmentOnBoardingBinding by lazy {
        FragmentOnBoardingBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Lista de fragmentos de onboarding
        val fragmentList = arrayListOf(
            FirstOnBoarding(),
            SecondOnBoarding(),
            ThirdOnBoarding()
        )

        // Adaptador para o ViewPager, que gerencia os fragmentos de onboarding
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        // Configura o adaptador no ViewPager
        binding.viewPager.adapter = adapter

        // Configura o indicador de pontos para o ViewPager
        val indicator = binding.dotsIndicator
        indicator.attachTo(binding.viewPager)
    }
}
