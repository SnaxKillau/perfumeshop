package kh.edu.rupp.ite.perfume_shop.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import kh.edu.rupp.ite.perfume_shop.api.model.Login
import kh.edu.rupp.ite.perfume_shop.api.model.Register
import kh.edu.rupp.ite.perfume_shop.api.service.LoginApiService
import kh.edu.rupp.ite.perfume_shop.api.service.RegisterApiService
import kh.edu.rupp.ite.perfume_shop.utility.AppEncryptedPreference
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterViewModel(): ViewModel() {

    suspend fun register(name: String ,email: String, password: String, context: Context): Boolean {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val httpClient = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8888")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        try {
            val registerApiService: RegisterApiService = httpClient.create(RegisterApiService::class.java)
            val task: Register = registerApiService.register(name, email, password)
            Log.d("Task", task.toString())
            AppEncryptedPreference.get(context).storeApiToken(task.token)

            return true
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            return false
        }
    }
}