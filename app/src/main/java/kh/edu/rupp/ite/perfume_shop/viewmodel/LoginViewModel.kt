package kh.edu.rupp.ite.perfume_shop.viewmodel


import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import kh.edu.rupp.ite.perfume_shop.api.model.ApiData
import kh.edu.rupp.ite.perfume_shop.api.model.Categories
import kh.edu.rupp.ite.perfume_shop.api.model.Login
import kh.edu.rupp.ite.perfume_shop.api.service.LoginApiService
import kh.edu.rupp.ite.perfume_shop.utility.AppEncryptedPreference
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginViewModel() : ViewModel() {

    suspend fun login(email: String, password: String, context: Context): Boolean {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val httpClient = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8888")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        try {
            val loginApiService: LoginApiService = httpClient.create(LoginApiService::class.java)
            val task: Login = loginApiService.login(email, password)
            Log.d("Task", task.toString())
            AppEncryptedPreference.get(context).storeApiToken(task.token)

            return true
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            return false
        }
    }
}

