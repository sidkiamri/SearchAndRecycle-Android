package com.example.recycleview.pojo

import com.google.gson.annotations.SerializedName

data class TypeXX(
    @SerializedName("_id")

    val id: String,
    @SerializedName("type_name")

    val typename: String,
    @SerializedName("createdAt")

    val createdAt: String,
    @SerializedName("description")

    val description: String,
    @SerializedName("typeImage")

    val typeImage: String,
)