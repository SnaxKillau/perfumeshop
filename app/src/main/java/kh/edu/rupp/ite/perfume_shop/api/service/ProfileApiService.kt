package kh.edu.rupp.ite.perfume_shop.api.service

import kh.edu.rupp.ite.perfume_shop.api.model.ProfileResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ProfileApiService {
    @GET("/api/profile")
    fun profile(): Call<ProfileResponse>

    @FormUrlEncoded
    @POST("/api/updateProfile")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    );
    @FormUrlEncoded
    @POST("/api/updateProfile")
    suspend fun update(
        @Field("email") email: String,
        @Field("name") name: String
    );
    @POST("/api/logout")
    suspend fun logout();
}