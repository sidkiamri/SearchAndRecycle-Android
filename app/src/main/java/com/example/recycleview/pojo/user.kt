package com.example.recycleview.pojo

import com.google.gson.annotations.SerializedName

data class user(
    @SerializedName("mesaage")

    val message: String,
    @SerializedName("FirstName")

    val FirstName: String,
    @SerializedName("LastName")

    val LastName: String,
    @SerializedName("_id")

    val id: String,
    @SerializedName("address")

    val address: String,
    @SerializedName("avatar")

    val avatar: String,
    @SerializedName("city")

    val city: String,
    @SerializedName("codePostal")

    val codePostal: String,
    @SerializedName("Phone")
    val phone: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("email")

    val email: String,
    @SerializedName("points")

    val points: Int,
    @SerializedName("token")

    val token: String
)