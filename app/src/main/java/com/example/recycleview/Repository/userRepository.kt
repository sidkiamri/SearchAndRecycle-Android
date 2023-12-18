package com.example.recycleview.Repository

import com.example.recycleview.pojo.*
import com.example.recycleview.retrofit.UserApi
import retrofit2.Response

class userRepository {

    suspend fun loginUser(loginRequest: LoginRequest): Response<user>? {
        return  UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
    suspend fun registerUser(registerRequest: RegisterRequest): Response<user>? {
        return  UserApi.getApi()?.registerUser(registerRequest = registerRequest)
    }
}