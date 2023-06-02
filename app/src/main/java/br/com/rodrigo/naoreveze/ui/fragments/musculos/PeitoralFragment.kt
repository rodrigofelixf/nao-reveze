package br.com.rodrigo.naoreveze.ui.fragments.musculos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.FragmentPeitoralBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class PeitoralFragment : Fragment() {
    private val binding by lazy { FragmentPeitoralBinding.inflate(layoutInflater) }

    // captura a view do bottomnavigation
    private val bottomNavigationView by lazy { requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation) }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // esconde o bottomnavigatiom
        bottomNavigationView.visibility = View.GONE


        val slideRight = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_right)
        binding.txtPeitoralSubistituicao.startAnimation(slideRight)

        binding.iconBack.setOnClickListener {
            findNavController().popBackStack()
        }




    }

    override fun onDestroy() {
        super.onDestroy()
        bottomNavigationView.visibility = View.VISIBLE
        
    }


}