package com.example.recycleview.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recycleview.Repository.userRepository
import com.example.recycleview.pojo.BaseResponse
import com.example.recycleview.pojo.RegisterRequest
import com.example.recycleview.pojo.user
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application)  {

    private val userRepo = userRepository()
    val registerResult: MutableLiveData<BaseResponse<user>> = MutableLiveData()

    fun registerUser(FirstName:String ,LastName: String, email: String,Phone:String,country:String,address:String,city:String,codePostal:String,password:String) {

        registerResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val registerRequest = RegisterRequest(
                    FirstName = FirstName,
                    LastName = LastName,
                    Email = email,
                    Phone = Phone,
                    Country = country,
                    Address = address,
                    City = city,
                    CodePostal = codePostal,
                    Password = password

                )
                val response = userRepo.registerUser(registerRequest = registerRequest)
                if (response?.code() == 201) {
                    Log.d("RegisterActivity",response.body().toString())
                    Log.d("RegisterActivity",""+response.body())
                    registerResult.value = BaseResponse.Success(response.body())
                }

                else {
                    Log.d("RegisterActivity",response?.code().toString())
                    registerResult.value = BaseResponse.Error(response?.message())
                }
            } catch (ex: Exception) {
                registerResult.value = BaseResponse.Error(ex.message)
            }
        }
    }


}