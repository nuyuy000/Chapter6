package com.example.chapter5.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // BASE_URL merupakan URL default untuk mengkoneksikan aplikasi dengan endpoint pada API
    const val BASE_URL = "https://api.themoviedb.org/3/"


    private val logging: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

//    private val client = OkHttpClient.Builder()
//        .addInterceptor(logging)
//        .build()

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor {
            val request = it.request()
            val queryBuild = request.url
                .newBuilder()
                .addQueryParameter("api_key", "263fe73b9ad23b1e66f27f621e001e83").build()
            return@addInterceptor it.proceed(request.newBuilder().url(queryBuild).build())
        }
        .build()


    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(ApiService::class.java)
    }

    val instance2: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(ApiService::class.java)
    }
}