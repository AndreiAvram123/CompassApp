package com.andrei.dataLayer.engine.core

import java.lang.Exception

sealed class DataResult<out T > {

    data class Success<out T >(val data: T) : DataResult<T>()
    object Loading : DataResult<Nothing>()
    data class Error(val exception: Exception) : DataResult<Nothing>()

}