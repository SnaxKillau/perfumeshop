package kh.edu.rupp.ite.perfume_shop.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import kh.edu.rupp.ite.perfume_shop.api.client.ApiInterceptor
import kh.edu.rupp.ite.perfume_shop.api.model.Product
import kh.edu.rupp.ite.perfume_shop.api.model.ProductApiData
import kh.edu.rupp.ite.perfume_shop.api.model.ProductDetailApiData
import kh.edu.rupp.ite.perfume_shop.api.model.ProductDetailResponse
import kh.edu.rupp.ite.perfume_shop.api.model.Profile
import kh.edu.rupp.ite.perfume_shop.api.model.ProfileApiData
import kh.edu.rupp.ite.perfume_shop.api.model.ProfileResponse
import kh.edu.rupp.ite.perfume_shop.api.model.Status
import kh.edu.rupp.ite.perfume_shop.api.service.ProfileApiService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class ProfileViewModel : ViewModel() {


    private val _profileData: MutableLiveData<ProfileApiData> = MutableLiveData()
    val profileData: LiveData<ProfileApiData>
        get() = _profileData

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor())
        .build()
     fun profile() {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val httpClient = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8888")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

            val profileApiService: ProfileApiService = httpClient.create(ProfileApiService::class.java)
            val task: Call<ProfileResponse> = profileApiService.profile()

        task.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {

                if (response.isSuccessful) {

                    val apiData = ProfileApiData(Status.SUCCESS, response.body())
                    Log.d("Data" , response.body().toString())
                    _profileData.postValue(apiData)
                } else {
                    val apiData = ProfileApiData(Status.ERROR, response.body())
                    Log.d("Error122" , response.toString())
                    _profileData.postValue(apiData)
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                val apiData = ProfileApiData(Status.ERROR, null)
                Log.d("Error" , t.message.toString())
                _profileData.postValue(apiData)

            }
        })
    }
    suspend fun logout(): Boolean {
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
            val task = profileApiService.logout()
            Log.d("Task", task.toString())
            return true
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            return false
        }
    }




}

