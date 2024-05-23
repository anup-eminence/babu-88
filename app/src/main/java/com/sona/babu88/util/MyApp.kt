package com.sona.babu88.util

import android.app.Application

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MySharedPreferences.init(applicationContext)
    }
}