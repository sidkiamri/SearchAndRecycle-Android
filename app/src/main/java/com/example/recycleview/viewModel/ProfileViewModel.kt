package com.example.recycleview.viewModel

import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.*
import com.example.recycleview.Repository.ProfileRepository
import com.example.recycleview.Repository.userRepository
import com.example.recycleview.pojo.Materiel
import com.example.recycleview.pojo.Resource
import com.example.recycleview.pojo.Resource.Companion.loading
import com.example.recycleview.pojo.materielList
import com.example.recycleview.pojo.user
import com.example.recycleview.retrofit.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Response.error
import retrofit2.Response.success

class ProfileViewModel() : ViewModel(){
    val userRepo = ProfileRepository()
val token:String
    get() {
return token   }
    private   var  DetailsLiveData= MutableLiveData<user?>()
    fun loadProfile() {
        val call = BasicAuthClient<DemoRemoteService>().create(DemoRemoteService::class.java).getProfile()

        call.enqueue(object: Callback<user> {
            override fun onFailure(call: Call<user>, t: Throwable) {
                Log.e("DemoClass", t.message, t)
            }

            override fun onResponse(call: Call<user>, response: Response<user>) {
                if (response.isSuccessful) {
                    Log.i("DemoClass", "Profile Loaded.")
                    if (response.body() != null) {

                        val user: user? = response.body()
                        DetailsLiveData.value=user
                    } else {
                        return
                    }

                } else {
                    Log.e("DemoClass", "Error: ${response.code()} ${response.message()}")
                }
            }
        })
    }





    fun observerDetailsLiveData(): LiveData<user?> {
        return DetailsLiveData}}


