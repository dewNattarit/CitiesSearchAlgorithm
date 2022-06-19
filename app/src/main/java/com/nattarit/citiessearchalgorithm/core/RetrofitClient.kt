package com.nattarit.citiessearchalgorithm.core

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient constructor(private val context: Context) {
    fun build(path: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(path)
            .client(provideOkHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .build()
    }
    private fun provideOkHttpClient(
        context: Context,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideHttpLoggingInterceptor())
            .addInterceptor(NetworkConnectionInterceptor(context))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }
    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        logger.setLevel(
            HttpLoggingInterceptor.Level.BODY
        )

        return logger
    }

    private fun provideGson() : Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()
    }
}