package com.example.recycleview.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recycleview.Repository.userRepository
import com.example.recycleview.pojo.BaseResponse
import com.example.recycleview.pojo.LoginRequest
import com.example.recycleview.pojo.LoginResponse
import com.example.recycleview.pojo.user
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = userRepository()
    val loginResult: MutableLiveData<BaseResponse<user>> = MutableLiveData()

    fun loginUser(email: String, pwd: String) {

        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val loginRequest = LoginRequest(
                    password = pwd,
                    email = email
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.code() == 200) {
                    Log.d("LoggingActivity",response.body().toString())
                    Log.d("LoggingActivity",""+response.body())
                    loginResult.value = BaseResponse.Success(response.body())


                } else {
                    loginResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}