package br.com.rodrigo.naoreveze.fragments.musculos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.FragmentPeitoralBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class PeitoralFragment : Fragment() {
    private var _binding: FragmentPeitoralBinding? = null
    private val binding get() = _binding!!
    private val bottomNavigationView by lazy { requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation) }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeitoralBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // esconde o bottomnavigatiom
        bottomNavigationView.visibility = View.GONE


        val slideRight = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_right)
        binding.txtPeitoralSubistituicao.startAnimation(slideRight)




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        bottomNavigationView.visibility = View.VISIBLE
        
    }


}