package br.com.rodrigo.naoreveze.ui.fragments

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.rodrigo.naoreveze.databinding.FragmentHomeBinding
import java.util.Locale


class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
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


        initDataAtual()

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun initDataAtual() {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEEE, dd 'de' MMMM", Locale.getDefault())
        val currentDate = dateFormat.format(calendar.time)
        binding.textViewDataAtual.text = currentDate.toString()
    }



}

