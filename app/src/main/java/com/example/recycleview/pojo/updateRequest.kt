package com.example.recycleview.pojo

data class updateRequest(
    val points: Int
){
    fun getpoints(): Int {
        return points
    }
}