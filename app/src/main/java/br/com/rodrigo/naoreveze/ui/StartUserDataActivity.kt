package br.com.rodrigo.naoreveze.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import br.com.rodrigo.naoreveze.R
import br.com.rodrigo.naoreveze.databinding.ActivityStartUserDataBinding
import br.com.rodrigo.naoreveze.ui.fragments.HomeFragment
import br.com.rodrigo.naoreveze.ui.fragments.datafragments.UserNameFragment

class StartUserDataActivity : AppCompatActivity() {

    private val binding by lazy { ActivityStartUserDataBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_userdata) as NavHostFragment
        navController = navHostFragment.navController


    }

}