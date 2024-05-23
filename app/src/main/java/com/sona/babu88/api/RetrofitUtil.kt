package com.sona.babu88.api

import com.sona.babu88.api.auth.AuthorizationInterceptor
import com.sona.babu88.api.auth.DecryptionInterceptor
import com.sona.babu88.api.auth.EncryptionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtil {

    val apiServies : ApiServies=
        Retrofit.Builder()
            .baseUrl("https://api.olddata.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideHttpClient())
            .build()
            .create(ApiServies::class.java)

    private fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        return  HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    }

    private fun provideHttpClient(): OkHttpClient {
        return try {
            OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(EncryptionInterceptor())
                .addInterceptor(DecryptionInterceptor())
                .addInterceptor(AuthorizationInterceptor())
                .addInterceptor(provideHttpLoggingInterceptor()).build()
        } catch (e: Exception) {
            e.printStackTrace()
            OkHttpClient()
        }
    }
}