package com.example.paging_practice.data.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 2023-09-09
 * pureum
 */
object Remote {

    private var gson: Gson = GsonBuilder().setLenient().create()

    fun serviceRetrofit(): Retrofit{
        return Retrofit.Builder()
//            .baseUrl("http://210.119.104.158:8080/")
            .baseUrl("https://picsum.photos/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    //네트워크 통신 과정을 보기 위한 클라이언트
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor {
            val request = it.request()
                .newBuilder()
                .build()
            val response = it.proceed(request)
            response
        }
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()
}