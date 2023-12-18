package com.example.recycleview.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycleview.pojo.MaterielXX
import com.example.recycleview.pojo.materielbycategory
import com.example.recycleview.pojo.typeList
import com.example.recycleview.retrofit.MaterielApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryModelView: ViewModel() {
    val matrielLiveData = MutableLiveData<List<MaterielXX>>()


    fun getMaterielByCategory(categoryid: String) {

        MaterielApi.getApi()?.getMaterielbycategory(categoryid)
            ?.enqueue(object : Callback<materielbycategory> {
                override fun onResponse(
                    call: Call<materielbycategory>,
                    response: Response<materielbycategory>
                ) {
                    response.body()?.let { materielbycategory ->
                        matrielLiveData.postValue(materielbycategory.materiel)

                    }
                }

                override fun onFailure(call: Call<materielbycategory>, t: Throwable) {
                    Log.d("CategoryMaterielviewmodel", t.message.toString())
                }


            })
    }

    fun observableMaterielLiveData(): LiveData<List<MaterielXX>> {
return matrielLiveData
    }















}