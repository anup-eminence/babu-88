package com.sona.babu88.api.auth

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.sona.babu88.api.RetrofitUtil
import com.sona.babu88.util.AppConstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

object CheckUserLogin {

    var checkUserLogin = MutableLiveData<Any>()

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    data class ScreatKey(
        val timeStamp: String,
        val secretKey: String
    )


    fun startLoginCheck() {
        runnable = Runnable {
            val request = AppConstant.getTimeStamp()
            println(">>>>>timestamp ${request[AppConstant.TIMESTAMP]}")
            println(">>>>>SECRET_KEY ${request[AppConstant.SECRET_KEY]}")
            CoroutineScope(Dispatchers.IO).launch {
                val scretKey = ScreatKey(
                    timeStamp = request[AppConstant.TIMESTAMP].toString(),
                    secretKey = request[AppConstant.SECRET_KEY] ?: ""
                )
                val verifyUser = RetrofitUtil.apiServies.verifyUser(scretKey)
                if (verifyUser.isSuccessful) {
                    val getLogin = RetrofitUtil.apiServies.getLoginToken(scretKey)
                    if (getLogin.isSuccessful) {
                        checkUserLogin.postValue(getLogin.body())
                    } else {
                        checkUserLogin.postValue("Error Occured")
                    }
                }
            }

            handler.postDelayed(runnable, 3000)
        }
        handler.post(runnable)
    }

    fun stopLoginCheck() {
        handler.removeCallbacks(runnable)
    }
}
