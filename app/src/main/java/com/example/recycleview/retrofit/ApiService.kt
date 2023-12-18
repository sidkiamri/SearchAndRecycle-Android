package com.example.recycleview.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    var YOUR_TOKEN = ""

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://nodeserver-38hw.vercel.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder().addHeader("Authorization", "Bearer ${YOUR_TOKEN}").build()
            chain.proceed(request)
        }.build())
        .build()

    var service: UserApi = retrofit.create(UserApi::class.java)
        private set

}