package kh.edu.rupp.ite.perfume_shop.api.service

import kh.edu.rupp.ite.perfume_shop.api.model.ProductCategoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductCategoryApiService {
    @GET("/api/productCategory/{categoryID}")
    fun getCategoryById(@Path("categoryID") categoryID: Int): Call<ProductCategoryResponse>
}