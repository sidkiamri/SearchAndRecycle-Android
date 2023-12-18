package com.example.recycleview.retrofit

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.recycleview.Fragment.HomeFragment
import com.example.recycleview.activity.*
import com.example.recycleview.app.MyApp

import com.example.recycleview.retrofit.RetrofitInstance.client
import com.example.recycleview.retrofit.sessionManager.USER_TOKEN
import com.example.recycleview.retrofit.sessionManager.getToken
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BasicAuthClient <T> {





    private val client =  OkHttpClient.Builder()
        .addInterceptor(OAuthInterceptor("Bearer", sessionManager.getToken(MyApp.context).toString()
        ))
        .build()

    val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://nodeserver-38hw.vercel.app/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun create(service: Class<T>): T {
        return retrofit.create(service)
    }}