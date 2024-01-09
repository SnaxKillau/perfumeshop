package kh.edu.rupp.ite.perfume_shop.utility

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey



class AppEncryptedPreference private constructor(context: Context){

    private lateinit var pref: SharedPreferences

    init {
        val masterKey = MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
        pref = EncryptedSharedPreferences.create(
            context,
            "myadppenc",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun storeApiToken(token: String) {
        pref.edit().putString(KEY_TOKEN, token).apply()
    }

    fun getApiToken(): String? {
        return pref.getString(KEY_TOKEN, null)
    }

    companion object {

        private const val KEY_TOKEN = "token"

        private var instance: AppEncryptedPreference? = null

        fun get(context: Context): AppEncryptedPreference {
            if (instance == null) {
                instance = AppEncryptedPreference(context)
            }

            return instance!!
        }

    }

}
