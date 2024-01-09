package kh.edu.rupp.ite.perfume_shop.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kh.edu.rupp.ite.perfume_shop.api.service.LoginApiService
import kh.edu.rupp.ite.perfume_shop.core.AppCore
import kh.edu.rupp.ite.perfume_shop.databinding.ActivityLoginBinding
import kh.edu.rupp.ite.perfume_shop.utility.AppEncryptedPreference
import kh.edu.rupp.ite.perfume_shop.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

class LoginActivity(): BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel = LoginViewModel();
        binding.loginbut.setOnClickListener { startMainActivity() }
    }

    private fun startMainActivity() {
        showProgressDialog("Loading")
        val email = binding.email.text.toString();
        Log.d("email", email);
        val password = binding.password.text.toString();
        lifecycleScope.launch {
            val loginSuccessful =
                loginViewModel.login(email, password, AppCore.get().applicationContext)

            if (loginSuccessful) {
                hideProgressDialog()
                val token = AppEncryptedPreference.get(AppCore.get().applicationContext).getApiToken()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }



    }
}


}
