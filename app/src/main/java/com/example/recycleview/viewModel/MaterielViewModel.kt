package com.example.recycleview.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycleview.pojo.Materiel
import com.example.recycleview.pojo.materielList
import com.example.recycleview.retrofit.MaterielApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MaterielViewModel :ViewModel(){
    private   var  MaterielDetailsLiveData=MutableLiveData<Materiel>()
     fun getMaterielDetails(id:String){
        MaterielApi.getApi()?.GetMaterialsById(id)?.enqueue(object : Callback<materielList> {
            override fun onResponse(call: Call<materielList>, response: Response<materielList>) {
if(response.body()!=null){
  //  MaterielDetailsLiveData.value=response.body()!!.Materiels[0]

}else
{
    return
}

            }

            override fun onFailure(call: Call<materielList>, t: Throwable) {
Log.d("MaterielActivity",t.message.toString())            }

        })
}
    fun observerMaterielDetailsLiveData():LiveData<Materiel>{
        return MaterielDetailsLiveData

    }
}