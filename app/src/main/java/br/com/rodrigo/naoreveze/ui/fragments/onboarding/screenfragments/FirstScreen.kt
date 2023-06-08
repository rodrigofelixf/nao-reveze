package br.com.rodrigo.naoreveze.ui.fragments.onboarding.screenfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.FragmentFirstScreenBinding


class FirstScreen : Fragment() {

    private val binding: FragmentFirstScreenBinding by lazy {
        FragmentFirstScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)

        binding.buttonNext.setOnClickListener {
            viewPager?.currentItem = 1
        }
    }


}