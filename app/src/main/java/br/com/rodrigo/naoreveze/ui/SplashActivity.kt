package br.com.rodrigo.naoreveze.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.rodrigo.naoreveze.application.NaoRevezeApplication
import br.com.rodrigo.naoreveze.databinding.ActivitySplashBinding
import br.com.rodrigo.naoreveze.ui.viewmodel.UserViewModel
import br.com.rodrigo.naoreveze.ui.viewmodel.UserViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    private val userViewModel : UserViewModel by viewModels {
        UserViewModelFactory((application as NaoRevezeApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.binding.root)


        lifecycleScope.launch {
            delay(2_000)
            userViewModel.getCurrentUser().observe(this@SplashActivity) { user ->
                if (user != null) {
                    // Usuário já cadastrado, iniciar a HomeFragment pela Main Activity
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // Usuário não cadastrado, iniciar a tela de cadastro de usuario pela UserDataActivity
                    val intent = Intent(this@SplashActivity, StartUserDataActivity::class.java)
                    startActivity(intent)
                }
                finish()
            }
        }
    }
}