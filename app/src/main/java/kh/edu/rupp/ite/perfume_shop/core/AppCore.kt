package kh.edu.rupp.ite.perfume_shop.core

import android.app.Application


class AppCore : Application() {

    companion object {
        private lateinit var instance: AppCore

        fun get(): AppCore {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}

