package com.example.recycleview.pojo

interface resultListener <S> {
    fun onSuccess(successModel: S)
    fun onFail(any: String?)
    fun onError(e: Throwable?)
}