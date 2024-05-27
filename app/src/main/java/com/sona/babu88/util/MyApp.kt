package com.sona.babu88.util

import android.app.Application
import com.sona.babu88.api.auth.CheckUserLogin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MySharedPreferences.init(applicationContext)
        if (MySharedPreferences.readString(AppConstant.TOKEN,"").isNullOrEmpty().not()){
           // CheckUserLogin.startLoginCheck()
        }
    }


}