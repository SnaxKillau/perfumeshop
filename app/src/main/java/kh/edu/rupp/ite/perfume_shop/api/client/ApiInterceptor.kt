package kh.edu.rupp.ite.perfume_shop.api.client


import android.util.Log
import kh.edu.rupp.ite.perfume_shop.core.AppCore
import kh.edu.rupp.ite.perfume_shop.utility.AppEncryptedPreference
import kh.edu.rupp.ite.perfume_shop.utility.AppPreference
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = AppEncryptedPreference.get(AppCore.get().applicationContext).getApiToken();
        Log.d("T" , token.toString());
        val request = if(token != null) {
            chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
        } else {
            chain.request()
        }

        return chain.proceed(request)
    }

}
