package com.vikram.trendingrepos.data.model

sealed class NetworkResponse<out T:Any> {
    object Loading: NetworkResponse<Nothing>()
    data class Success<out T : Any>(val data: T) : NetworkResponse<T>()
    data class ResponseError(val throwable: Throwable) : NetworkResponse<Nothing>()
}