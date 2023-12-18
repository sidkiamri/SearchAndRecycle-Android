package com.example.recycleview.pojo

import com.google.gson.annotations.SerializedName

data class updateResponse(
    @SerializedName("points")

    val points: Int,
    @SerializedName("data")
    var `data`: Data,
    val token: String
) {


    data class Data(
        @SerializedName("points")
        var points: String,

    )


    }
