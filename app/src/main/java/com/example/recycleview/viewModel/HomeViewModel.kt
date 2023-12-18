package com.example.recycleview.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycleview.pojo.*
import com.example.recycleview.retrofit.BasicAuthClient
import com.example.recycleview.retrofit.DemoRemoteService
import com.example.recycleview.retrofit.MaterielApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
private var popularMaterielLiveData=MutableLiveData<List<MaterielX>>()
private var randomMaterielLiveData=MutableLiveData<Materiel>()
private var categoriesLiveData=MutableLiveData<List<TypeXX>>()
class HomeViewModel:ViewModel() {
   fun getRandomMateriel(){
       MaterielApi.getApi()?.Random()?.enqueue(object : Callback<materielList> {
           override fun onResponse(call: Call<materielList>, response: Response<materielList>) {
               if (response.body() != null) {

                   val random: Materiel = response.body()!!.Materiels[0]
randomMaterielLiveData.value=random
               } else {
                   return
               }
           }

           override fun onFailure(call: Call<materielList>, t: Throwable) {
               Log.d("HomeFragment", t.message.toString())
           }
       })
   }
    fun getPopularMateriel(){

        val call = BasicAuthClient<DemoRemoteService>().create(DemoRemoteService::class.java).getfavoriMateriel()

        call.enqueue(object: Callback<Mat> {
            override fun onFailure(call: Call<Mat>, t: Throwable) {
                Log.e("DemoClass", t.message, t)
            }

            override fun onResponse(call: Call<Mat>, response: Response<Mat>) {
                if (response.isSuccessful) {
                    Log.i("DemoClass", "Profile Loaded.")
                    if (response.body() != null) {


                  popularMaterielLiveData.value= response.body()!!.materiel

                } }else {
                    Log.e("DemoClass", "Error: ${response.code()} ${response.message()}")
                }
            }
        })

    }
    fun getCategories(){
        MaterielApi.getApi()?.getcategories()?.enqueue(object : Callback<typeList> {
            override fun onResponse(call: Call<typeList>, response: Response<typeList>) {
response.body()?.let { typeList ->
categoriesLiveData.postValue(typeList.Types)
    }           }

            override fun onFailure(call: Call<typeList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })

        }

    fun ObserveRandomLiveData():LiveData<Materiel>{
        return randomMaterielLiveData
    }
    fun observerPopularItemsLiveData():LiveData<List<MaterielX>>{
        return popularMaterielLiveData
    }
    fun observablecategoriesLiveData():LiveData<List<TypeXX>>{
        return categoriesLiveData
    }
}