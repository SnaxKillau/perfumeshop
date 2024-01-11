package kh.edu.rupp.ite.perfume_shop.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import kh.edu.rupp.ite.perfume_shop.api.model.Profile
import kh.edu.rupp.ite.perfume_shop.api.service.ProfileApiService
import kh.edu.rupp.ite.perfume_shop.api.service.RegisterApiService
import kh.edu.rupp.ite.perfume_shop.utility.AppEncryptedPreference
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class ProfileViewModel : ViewModel() {


    val profileData: MutableLiveData<Profile?> = MutableLiveData()


    suspend fun profile(name: String, email: String, context: Context): Boolean {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val httpClient = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8888")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        try {
            val profileApiService: ProfileApiService = httpClient.create(ProfileApiService::class.java)
            val task: Profile = profileApiService.profile(name, email)
            Log.d("Task", task.toString())
            AppEncryptedPreference.get(context).storeApiToken(task.token)


            profileData.postValue(task)
            return true
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            return false

        }
    }
}


//class ProfileViewModel (): ViewModel() {
//
//    suspend fun profile(name: String ,email: String,context: Context): Boolean {
//        val gson = GsonBuilder()
//            .setLenient()
//            .create()
//
//        val httpClient = Retrofit.Builder()
//            .baseUrl("http://10.0.2.2:8888")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//
//
//        try {
//            val profileApiService: ProfileApiService = httpClient.create(ProfileApiService::class.java)
//            val task: Profile = profileApiService.profile(name, email)
//            Log.d("Task", task.toString())
//            AppEncryptedPreference.get(context).storeApiToken(task.token)
//
//            return true
//        } catch (e: Exception) {
//            Log.e("Error", e.message.toString())
//            return false
//        }
//    }
//}