package br.com.rodrigo.naoreveze

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.rodrigo.naoreveze.databinding.FragmentHomeBinding
import br.com.rodrigo.naoreveze.databinding.FragmentPeitoralBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class PeitoralFragment : Fragment() {
    private var _binding: FragmentPeitoralBinding? = null
    private val binding get() = _binding!!

    private val bottomNavigationView by lazy {
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
    }
    private val imageView by lazy { requireActivity().findViewById<ImageView>(R.id.image_profile)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPeitoralBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationView.visibility = View.GONE
        imageView.visibility = View.GONE


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        bottomNavigationView.visibility = View.VISIBLE
        imageView.visibility = View.VISIBLE
    }


}