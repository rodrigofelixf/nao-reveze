package br.com.rodrigo.naoreveze.ui

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import br.com.rodrigo.naoreveze.databinding.ActivityMainBinding
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import br.com.rodrigo.naoreveze.R

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNavigation.itemActiveIndicatorColor = ColorStateList.valueOf(getColor(R.color.md_theme_dark_surfaceVariant)
        )

        initBottomNavigation()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(
                        R.id.homeFragment, null, NavOptions.Builder()
                            .setEnterAnim(R.anim.fade_in)
                            .setExitAnim(R.anim.fade_out)
                            .build()
                    )
                    true
                }

                R.id.treinoFragment -> {
                    navController.navigate(
                        R.id.treinoFragment, null, NavOptions.Builder()
                            .setEnterAnim(R.anim.fade_in)
                            .setExitAnim(R.anim.fade_out)
                            .build()
                    )
                    true
                }

                R.id.imcFragment -> {
                    navController.navigate(
                        R.id.imcFragment, null, NavOptions.Builder()
                            .setEnterAnim(R.anim.fade_in)
                            .setExitAnim(R.anim.fade_out)
                            .build()
                    )
                    true
                }

                R.id.perfilFragment -> {
                    navController.navigate(
                        R.id.perfilFragment, null, NavOptions.Builder()
                            .setEnterAnim(R.anim.fade_in)
                            .setExitAnim(R.anim.fade_out)
                            .build()
                    )
                    true
                }

                else -> false
            }
        }
    }

    private fun initBottomNavigation(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)
    }
}

