package com.example.recycleview.pojo

import com.google.gson.annotations.SerializedName

data class MaterielXX(
    @SerializedName("Like")

    val like: Boolean,
    @SerializedName("_id")

    val id: String,
    @SerializedName("comments")

    val comments: List<Any>,
    @SerializedName("description")

    val description: String,
    @SerializedName("materielName")

    val materielName: String,
    @SerializedName("matrielImage")

    val matrielImage: String,
    @SerializedName("type")

    val type: TypeXXX,
    @SerializedName("user")

    val user: Any
)