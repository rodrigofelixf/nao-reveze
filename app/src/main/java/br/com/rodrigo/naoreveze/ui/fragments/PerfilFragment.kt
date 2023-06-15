package br.com.rodrigo.naoreveze.ui.fragments

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet

import br.com.rodrigo.naoreveze.databinding.FragmentPerfilBinding


class PerfilFragment : Fragment() {
    private val binding: FragmentPerfilBinding by lazy {
        FragmentPerfilBinding.inflate(layoutInflater)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transitionSet = TransitionSet()
        transitionSet.addTransition(ChangeBounds())
        transitionSet.addTransition(Fade())
        transitionSet.duration = 200



        val cardView = binding.cardView
        val expandedLayout = binding.expandedLayout

        cardView.setOnClickListener {
            TransitionManager.beginDelayedTransition(cardView, transitionSet)
            expandedLayout.isVisible = !expandedLayout.isVisible
        }

    }
}