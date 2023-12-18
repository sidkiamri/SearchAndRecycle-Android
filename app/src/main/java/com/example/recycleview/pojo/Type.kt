package com.example.recycleview.pojo

import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("_id")

    val id: String,
    @SerializedName("type_name")

    val type_name: String
)