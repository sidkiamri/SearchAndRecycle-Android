package com.example.recycleview.retrofit

import android.provider.ContactsContract
import com.example.recycleview.pojo.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface DemoRemoteService {
    @GET("api/users/profile")
    fun getProfile(): Call<user>
    @GET("api/Materiel/getAllMateriel/slider")
    fun getfavoriMateriel(): Call<Mat>
    @PUT("api/users/points")
    suspend fun updatepoints(@Body updateRequest: updateRequest): Response<updateResponse>

}