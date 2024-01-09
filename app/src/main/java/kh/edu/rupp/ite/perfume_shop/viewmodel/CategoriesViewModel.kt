package kh.edu.rupp.ite.perfume_shop.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import kh.edu.rupp.ite.perfume_shop.api.client.ApiInterceptor
import kh.edu.rupp.ite.perfume_shop.api.model.ApiData
import kh.edu.rupp.ite.perfume_shop.api.model.Categories
import kh.edu.rupp.ite.perfume_shop.api.model.CategoryResponse
import kh.edu.rupp.ite.perfume_shop.api.model.Status
import kh.edu.rupp.ite.perfume_shop.api.service.CategoriesApiService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoriesViewModel: ViewModel() {

    private val _categoriesdata = MutableLiveData<ApiData<List<Categories>>>()

    val categoriesdata: LiveData<ApiData<List<Categories>>>
        get() = _categoriesdata

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor())
        .build()

    fun loadCategories(){
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val httpClient = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8888")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()


        val categoriesApiService: CategoriesApiService = httpClient.create(CategoriesApiService::class.java);
        val task: Call<CategoryResponse> = categoriesApiService.loadCategoriesList();

        task.enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                if (response.isSuccessful){

                    val apiData = ApiData<List<Categories>>(Status.SUCCESS, response.body())
                    _categoriesdata.postValue(apiData)
                    Log.d("H", response.body().toString())
                }else {
                    val apiData = ApiData<List<Categories>>(Status.ERROR, response.body())
                    _categoriesdata.postValue(apiData)
                    Log.d("h", "onResponse: F")
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {

                val apiData = ApiData<List<Categories>>(Status.ERROR, null)
                Log.d("errro" , t.message.toString())
                _categoriesdata.postValue(apiData)
            }
        })
    }
}