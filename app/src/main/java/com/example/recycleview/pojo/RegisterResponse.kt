package com.example.recycleview.pojo

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("id")
    var id: String,
    @SerializedName("message")
    var message: String
) {
    data class Data(
        @SerializedName("email")
        var Email: String,
        @SerializedName("id")
        var id: String,
        @SerializedName("Id")
        var id2: Int,
        @SerializedName("FirstName")
        var FirstName: String,
        @SerializedName("LastName")
        var LastName: String,
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
        var Password: String,
        @SerializedName("Token")
        var token: String
    )
}
