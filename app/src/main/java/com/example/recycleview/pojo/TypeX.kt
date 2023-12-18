package com.example.recycleview.pojo

import com.google.gson.annotations.SerializedName

data class TypeX(
    @SerializedName("_id")

    val id: String,
    @SerializedName("type_name")

    val typename: String
)