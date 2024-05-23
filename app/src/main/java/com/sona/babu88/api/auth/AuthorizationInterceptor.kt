package com.sona.babu88.api.auth

import MySharedPreferences
import com.sona.babu88.util.AppConstant
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val sessionId = "Your Session Id"
        request = request.newBuilder()
            .addHeader("Auth", "${MySharedPreferences.readString(AppConstant.TOKEN,"")}").build()
        return chain.proceed(request)
    }
}