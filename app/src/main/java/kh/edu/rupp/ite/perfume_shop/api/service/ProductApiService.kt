package kh.edu.rupp.ite.perfume_shop.api.service


import kh.edu.rupp.ite.perfume_shop.api.model.ProductDetailResponse
import kh.edu.rupp.ite.perfume_shop.api.model.ProductResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductApiService {
    @GET("/api/product")
    fun loadProductList(): Call<ProductResponse>

    @GET("/api/product/{productId}")
    fun getProductById(@Path("productId") productId: Int): Call<ProductDetailResponse>

    @POST("/api/addToBag/{productId}")
    fun addShoppingProduct(@Path("productId") productId: Int): Call<ProductResponse>

    @GET("/api/bag")
    fun getShoppingProduct():Call<ProductResponse>

    @DELETE("/api/pay")
    fun payShoppingProduct():Call<ProductResponse>
}