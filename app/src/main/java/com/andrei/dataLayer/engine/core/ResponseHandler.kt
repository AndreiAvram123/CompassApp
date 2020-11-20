package com.andrei.dataLayer.engine.core

import android.util.Log
import com.andrei.compassapp.BuildConfig

class ResponseHandler private constructor(){
    //  private val firebaseCrashlytics: FirebaseCrashlytics = FirebaseCrashlytics.getInstance()

      companion object {
         @JvmStatic
         fun getInstance() = ResponseHandler()
      }


    private val TAG = ResponseHandler::class.java.simpleName

    fun <T : Any> handleSuccess(data: T): DataResult<T> {
        return DataResult.Success(data)
    }

    fun <T > handleRequestException(e: Exception, url: String): DataResult<T> {
        Log.e(TAG,"Error with request $url")
        logException(e)
        return DataResult.Error(e)
    }

    private fun logException(e:Exception){
       Log.e(TAG, e.stackTraceToString())
        if(!BuildConfig.DEBUG){
         //  firebaseCrashlytics.recordException(e)
        }
    }
}