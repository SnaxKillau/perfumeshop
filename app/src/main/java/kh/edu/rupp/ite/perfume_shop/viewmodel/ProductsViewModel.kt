package kh.edu.rupp.ite.perfume_shop.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import kh.edu.rupp.ite.perfume_shop.api.client.ApiInterceptor
import kh.edu.rupp.ite.perfume_shop.api.model.ApiData
import kh.edu.rupp.ite.perfume_shop.api.model.Product
import kh.edu.rupp.ite.perfume_shop.api.model.ProductApiData
import kh.edu.rupp.ite.perfume_shop.api.model.ProductResponse
import kh.edu.rupp.ite.perfume_shop.api.model.Status
import kh.edu.rupp.ite.perfume_shop.api.service.ProductApiService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsViewModel : ViewModel(){

    private val _productData = MutableLiveData<ProductApiData<List<Product>>>()
    val productData: LiveData<ProductApiData<List<Product>>>
        get() = _productData

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor())
        .build()
    fun loadProductsFromApi(){
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val httpClient = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8888")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        val productApiService: ProductApiService = httpClient.create(ProductApiService::class.java);
        val task: Call<ProductResponse> = productApiService.loadProductList();




        task.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {

                if (response.isSuccessful) {

                    val apiData = ProductApiData<List<Product>>(Status.SUCCESS, response.body())
                    Log.d("Data" , response.body()?.data.toString())
                    _productData.postValue(apiData)
                } else {
                    val apiData = ProductApiData<List<Product>>(Status.ERROR, response.body())
                    _productData.postValue(apiData)
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                val apiData = ProductApiData<List<Product>>(Status.ERROR, null)
                _productData.postValue(apiData)

            }
        })

    }

}