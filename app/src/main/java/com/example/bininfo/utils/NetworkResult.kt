package com.example.bininfo.utils

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: Status? = null
) {
    class Success<T>(data: T?): NetworkResult<T>(data = data)
    class Error<T>(message: Status?): NetworkResult<T>(message = message)
    class Loading<T>: NetworkResult<T>()
}