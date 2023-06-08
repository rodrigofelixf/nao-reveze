package br.com.rodrigo.naoreveze.ui.fragments.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.FragmentImcBinding
import br.com.rodrigo.naoreveze.databinding.FragmentOnBoardingBinding
import br.com.rodrigo.naoreveze.ui.adapter.ViewPagerAdapter
import br.com.rodrigo.naoreveze.ui.fragments.onboarding.screenfragments.FirstScreen
import br.com.rodrigo.naoreveze.ui.fragments.onboarding.screenfragments.SecondScreen
import br.com.rodrigo.naoreveze.ui.fragments.onboarding.screenfragments.ThirdScreen


class OnBoardingFragment : Fragment() {

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

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter
        val indicator = binding.dotsIndicator

        indicator.attachTo(binding.viewPager)
    }



}