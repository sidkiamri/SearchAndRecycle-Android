package com.example.recycleview.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycleview.pojo.*
import com.example.recycleview.retrofit.MaterielApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventModelView: ViewModel() {
    val eventLiveData = MutableLiveData<List<Event>>()


    fun getMaterielByCategory() {

        MaterielApi.getApi()?.getevents()
            ?.enqueue(object : Callback<events> {
                override fun onResponse(call: Call<events>, response: Response<events>) {
                    response.body()?.let { events ->
                        eventLiveData.postValue(events.event)
                    }
                }

                override fun onFailure(call: Call<events>, t: Throwable) {
                    Log.d("eventFragment", t.message.toString())
                }


            })
    }

    fun observableMaterielLiveData(): LiveData<List<Event>> {
return eventLiveData
    }















}