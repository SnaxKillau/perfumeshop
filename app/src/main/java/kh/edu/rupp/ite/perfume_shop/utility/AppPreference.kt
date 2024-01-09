package kh.edu.rupp.ite.perfume_shop.utility

import android.content.Context
import android.content.SharedPreferences

class AppPreference private constructor(context: Context){

    private lateinit var pref: SharedPreferences

    init {
        pref = context.getSharedPreferences("myapp", Context.MODE_PRIVATE)
    }

    companion object {

        private var instance: AppPreference? = null

        fun get(context: Context): AppPreference {
            if (instance == null) {
                instance = AppPreference(context)
            }

            return instance!!
        }

    }

}
