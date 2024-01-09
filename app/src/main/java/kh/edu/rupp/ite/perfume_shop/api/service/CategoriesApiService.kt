package kh.edu.rupp.ite.perfume_shop.api.service

import kh.edu.rupp.ite.perfume_shop.api.model.Categories
import kh.edu.rupp.ite.perfume_shop.api.model.CategoryResponse
import retrofit2.Call
import retrofit2.http.GET

interface CategoriesApiService {


    @GET("/api/category")
    fun loadCategoriesList(): Call<CategoryResponse>
}