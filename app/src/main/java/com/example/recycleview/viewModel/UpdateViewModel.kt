package com.example.recycleview.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recycleview.Repository.userRepository
import com.example.recycleview.pojo.*
import com.example.recycleview.retrofit.BasicAuthClient
import com.example.recycleview.retrofit.DemoRemoteService
import kotlinx.coroutines.launch

class UpdateViewModel (application: Application) : AndroidViewModel(application)  {

    private val userRepo = userRepository()
    val updateResult: MutableLiveData<BaseResponse<updateResponse>> = MutableLiveData()

    fun updatepoints(points:Int) {

        updateResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val updateRequest = updateRequest(
                   points = points

                )
                val response = BasicAuthClient<DemoRemoteService>().create(DemoRemoteService::class.java).updatepoints(updateRequest = updateRequest)
                if (response?.code() == 200) {
                    Log.d("materielPostActivity",response.body().toString())
                    Log.d("materielPostActivity",""+response.body())
                    updateResult.value = BaseResponse.Success(response.body())
                }

                else {
                    Log.d("RegisterActivity",response?.code().toString())
                    updateResult.value = BaseResponse.Error(response?.message())
                }
            } catch (ex: Exception) {
                updateResult.value = BaseResponse.Error(ex.message)
            }
        }
    }


}