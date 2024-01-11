package kh.edu.rupp.ite.perfume_shop.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import kh.edu.rupp.ite.perfume_shop.api.client.ApiInterceptor
import kh.edu.rupp.ite.perfume_shop.api.service.ProfileApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditViewModel : ViewModel() {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor())
        .build()
    suspend fun edit(email: String, name: String): Boolean {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val httpClient = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8888")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

        try {
            val profileApiService: ProfileApiService = httpClient.create(ProfileApiService::class.java)
            val task = profileApiService.update(email,name)
            Log.d("Task", task.toString())
            return true
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            return false
        }
    }

}