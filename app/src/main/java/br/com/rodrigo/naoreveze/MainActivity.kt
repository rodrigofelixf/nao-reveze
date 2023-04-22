package br.com.rodrigo.naoreveze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.rodrigo.naoreveze.databinding.ActivityMainBinding
import br.com.rodrigo.naoreveze.fragments.HomeFragment
import br.com.rodrigo.naoreveze.fragments.TreinoFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menu_perfil1 -> {
                    openFragment(HomeFragment())
                    true
                }
                R.id.menu_perfil2 -> {
                    openFragment(TreinoFragment())
                    true
                }
                else -> false
            }
        }

    }

    override fun onResume() {
        super.onResume()
        // Define a tela inicial ao logar como o Treino
        binding.bottomNavigation.selectedItemId = R.id.menu_perfil2
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.slide_in, R.anim.slide_out)
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}