package kh.edu.rupp.ite.perfume_shop.api.service

import kh.edu.rupp.ite.perfume_shop.api.model.Profile
import kh.edu.rupp.ite.perfume_shop.api.model.Register
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProfileApiService {


    @FormUrlEncoded

    @GET("/api/profile")
    suspend fun profile(
        @Field("name") name: String,
        @Field("email") email: String,
    ): Profile
}