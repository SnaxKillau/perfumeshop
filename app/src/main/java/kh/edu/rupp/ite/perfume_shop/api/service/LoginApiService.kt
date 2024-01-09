package kh.edu.rupp.ite.perfume_shop.api.service

import android.provider.ContactsContract.CommonDataKinds.Email
import kh.edu.rupp.ite.perfume_shop.api.model.Login
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApiService {
    @FormUrlEncoded
    @POST("/api/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Login
}