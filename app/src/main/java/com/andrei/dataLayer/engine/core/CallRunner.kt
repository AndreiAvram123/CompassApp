package com.andrei.dataLayer.engine.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import okhttp3.Interceptor
import retrofit2.Call
import retrofit2.awaitResponse

class CallRunner (private val responseHandler: ResponseHandler){


     fun < T> makeObservableCall(call: Call<T>,completion : suspend (data:T)->Unit)  = liveData<Result<T>> {
         emit(Result.Loading)
         val url = call.request().url.toString()
         try {
             val response =  call.awaitResponse()
             val body = response.body()
             if (body != null) {
                 completion(body)
                 emit(responseHandler.handleSuccess(body))
             }
        } catch (e: Exception) {
           emit(responseHandler.handleRequestException(e,url))
        }
    }
    suspend fun <T> makeCall(call: Call<T>, completion:suspend (data:T)->Unit): Result<T> {

        val url = call.request().url.toString()
        try {
            val response = call.awaitResponse()
            if(response.isSuccessful){
                val body = response.body()
                if(body !=null){
                    completion(body)
                    return responseHandler.handleSuccess(body)
                }
            }
        } catch (e: Exception) {
            return responseHandler.handleRequestException(e,url)
        }
        return responseHandler.handleRequestException(Exception("Unknown"),url)
    }





}