    package com.sona.babu88.api.auth

    import com.sona.babu88.api.auth.AuthUtils
    import okhttp3.Interceptor
    import okhttp3.MediaType
    import okhttp3.MediaType.Companion.toMediaTypeOrNull
    import okhttp3.RequestBody
    import okhttp3.RequestBody.Companion.toRequestBody
    import okhttp3.Response
    import okio.Buffer


    class EncryptionInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val originalBody = originalRequest.body

            if (originalBody != null) {
                val buffer = Buffer()
                originalBody.writeTo(buffer)
                val originalContent = buffer.readUtf8()
                println(">>>>>>originalContent $originalContent")
                val encryptedContent = AuthUtils.encryptData(originalContent).toString()
                val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
                println(">>>>>>encrptContent $encryptedContent")
                val encryptedBody = encryptedContent.toRequestBody(mediaType)

                val encryptedRequest = originalRequest.newBuilder()
                    .method(originalRequest.method, encryptedBody)
                    .header("Content-Type", "application/json")
                    .build()

                return chain.proceed(encryptedRequest)
            }
            return chain.proceed(originalRequest)
        }
    }
