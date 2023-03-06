package com.petproject.moviesapp.data.network.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "https://api.kinopoisk.dev/v1/"
    private const val TOKEN = "VQ6JH33-T1RMY1E-JN7S24Z-H9BBBJZ"
    private const val TOKEN_KEY = "X-API-KEY"

    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val authorizationInterceptor = Interceptor {
        val request = it.call().request().newBuilder().addHeader(TOKEN_KEY, TOKEN).build()
        it.proceed(request)
    }
    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authorizationInterceptor)
        .build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(httpClient)
        .build()

    val apiService = retrofit.create(ApiService::class.java)

}