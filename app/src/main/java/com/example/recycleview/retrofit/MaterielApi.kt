package com.example.recycleview.retrofit

import com.example.recycleview.pojo.*
import retrofit2.Call
import retrofit2.http.*

interface MaterielApi {
@GET("Materiel/getRandomMateriel")
fun getMateriels(): Call<materielList>

    @GET("/api/Materiel/getRandomMateriel")
    fun Random() :Call<materielList> ;

    @GET("/api/Materiel/getById/{id}")
    fun GetMaterialsById(@Path("id") id : String ):Call<materielList>
@GET("/api/Type/getAllty")
fun getcategories():Call<typeList>
@GET("/api/Materiel/getByType/{id}")
fun getMaterielbycategory(@Path("id") id : String ):Call<materielbycategory>
    @GET("/api/Event/getAllevent")
    fun getevents():Call<events>
    companion object {
        fun getApi(): MaterielApi? {
            return RetrofitInstance.createService(MaterielApi::class.java)
        }
    }
}