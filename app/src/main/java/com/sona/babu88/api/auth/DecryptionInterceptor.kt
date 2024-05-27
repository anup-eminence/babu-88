package com.sona.babu88.api.auth

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class DecryptionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        println(">>>>>originalResponse ${originalResponse.body}")

        if (!originalResponse.isSuccessful) {
            return originalResponse
        }

        val encryptedBody = originalResponse.body?.string() ?: return originalResponse

        val decryptedBody = AuthUtils.decryptData(encryptedBody)

        val contentType = originalResponse.header("Content-Type")
        val decryptedResponseBody = decryptedBody.toString()
        println(">>>>decryptedResponse $decryptedResponseBody")
        return originalResponse.newBuilder()
            .body(decryptedResponseBody.toResponseBody(contentType?.toMediaTypeOrNull()))
            .build()
    }
}