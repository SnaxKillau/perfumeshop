package kh.edu.rupp.ite.perfume_shop.viewmodel

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import kh.edu.rupp.ite.perfume_shop.api.client.ApiInterceptor
import kh.edu.rupp.ite.perfume_shop.api.model.ProductDetailApiData
import kh.edu.rupp.ite.perfume_shop.api.model.ProductDetailResponse
import kh.edu.rupp.ite.perfume_shop.api.model.Status
import kh.edu.rupp.ite.perfume_shop.api.service.ProductApiService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class ProductDetailViewModel : ViewModel(){

    private val _productData = MutableLiveData<ProductDetailApiData>()

    val productData: LiveData<ProductDetailApiData>
        get() = _productData


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor())
        .build()
    fun loadProductDetail(id:Int){
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val httpClient = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8888")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        val productApiService: ProductApiService = httpClient.create(ProductApiService::class.java)


                val task: Call<ProductDetailResponse> = productApiService.getProductById(id)
                Log.d("id" , id.toString())
                task.enqueue(object : Callback<ProductDetailResponse> {
                    override fun onResponse(call: Call<ProductDetailResponse>, response: Response<ProductDetailResponse>) {

                        if (response.isSuccessful) {

                            val apiData = ProductDetailApiData(Status.SUCCESS, response.body())
                            Log.d("Data" , response.body().toString())
                            _productData.postValue(apiData)
                        } else {
                            val apiData = ProductDetailApiData(Status.ERROR, response.body())
                            Log.d("Error122" , response.toString())
                            _productData.postValue(apiData)
                        }
                    }

                    override fun onFailure(call: Call<ProductDetailResponse>, t: Throwable) {
                        val apiData = ProductDetailApiData(Status.ERROR, null)
                        Log.d("Error" , t.message.toString())
                        _productData.postValue(apiData)

                    }
                })
            }
        }

