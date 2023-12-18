package com.example.recycleview.retrofit


import com.example.recycleview.pojo.LoginRequest
import com.example.recycleview.pojo.RegisterRequest
import com.example.recycleview.pojo.RegisterResponse
import com.example.recycleview.pojo.user
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @POST("api/users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<user>
    @POST("api/users")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<user>


    companion object {
        fun getApi(): UserApi? {
            return RetrofitInstance.createService(UserApi::class.java)
        }

    }
    //@GET("api/Materiel/getAllMateriel")
//suspend fun getDataromAPI(@Query("q") query: String):MList

}

