package com.example.recycleview.pojo

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("FirstName")
    var FirstName:String,
    @SerializedName("LastName")
    var LastName: String,
    @SerializedName("email")
    var Email: String,
    @SerializedName("phone")
    var Phone: String,
    @SerializedName("country")
    var Country: String,
    @SerializedName("address")
    var Address: String,
    @SerializedName("city")
    var City: String,
    @SerializedName("codePostal")
    var CodePostal: String,
    @SerializedName("password")
    var Password: String

)