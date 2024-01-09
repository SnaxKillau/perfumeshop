package kh.edu.rupp.ite.perfume_shop.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kh.edu.rupp.ite.perfume_shop.core.AppCore
import kh.edu.rupp.ite.perfume_shop.databinding.ActivityLoginBinding
import kh.edu.rupp.ite.perfume_shop.databinding.ActivityRegisterBinding
import kh.edu.rupp.ite.perfume_shop.utility.AppEncryptedPreference
import kh.edu.rupp.ite.perfume_shop.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

class RegisterActivity(): AppCompatActivity() {


    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerViewModel = RegisterViewModel();
        binding.loginbut.setOnClickListener{startLoginActivity()}
        binding.regibut.setOnClickListener {startMainActivity()}
    }

    private fun startMainActivity() {
//
//        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
//        startActivity(intent)
        val name = binding.username.text.toString();
        val email = binding.email.text.toString();
        Log.d("email", email);
        val password = binding.password.text.toString();
        lifecycleScope.launch {
            val registerSuccessful =
                registerViewModel.register(name, email, password, AppCore.get().applicationContext)

            if (registerSuccessful) {
                val token = AppEncryptedPreference.get(AppCore.get().applicationContext).getApiToken()
                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun startLoginActivity(){
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}