package kh.edu.rupp.ite.perfume_shop.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import kh.edu.rupp.ite.perfume_shop.api.client.ApiInterceptor
import kh.edu.rupp.ite.perfume_shop.api.model.Product
import kh.edu.rupp.ite.perfume_shop.api.model.ProductApiData
import kh.edu.rupp.ite.perfume_shop.api.model.ProductCategoryApiData
import kh.edu.rupp.ite.perfume_shop.api.model.ProductCategoryResponse
import kh.edu.rupp.ite.perfume_shop.api.model.ProductResponse
import kh.edu.rupp.ite.perfume_shop.api.model.Status
import kh.edu.rupp.ite.perfume_shop.api.service.ProductCategoryApiService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductCategoryViewModel: ViewModel() {

    private val _productData = MutableLiveData<ProductCategoryApiData<List<Product>>>()
    val productData: LiveData<ProductCategoryApiData<List<Product>>>
        get() = _productData

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor())
        .build()
    fun loadCategoryProduct(id:Int){
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val httpClient = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8888")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        val productCategoryApiService: ProductCategoryApiService = httpClient.create(ProductCategoryApiService::class.java)


        val task: Call<ProductCategoryResponse> = productCategoryApiService.getCategoryById(id)
        task.enqueue(object : Callback<ProductCategoryResponse> {
            override fun onResponse(call: Call<ProductCategoryResponse>, response: Response<ProductCategoryResponse>) {

                if (response.isSuccessful) {

                    val apiData = ProductCategoryApiData<List<Product>>(Status.SUCCESS, response.body())
                    Log.d("Data" , response.body()?.data.toString())
                    _productData.postValue(apiData)
                } else {
                    val apiData = ProductCategoryApiData<List<Product>>(Status.ERROR, response.body())
                    Log.d("Error122" , response.toString())
                    _productData.postValue(apiData)
                }
            }

            override fun onFailure(call: Call<ProductCategoryResponse>, t: Throwable) {
                val apiData = ProductCategoryApiData<List<Product>>(Status.ERROR, null)
                Log.d("Error" , t.message.toString())
                _productData.postValue(apiData)

            }
        })
    }
}