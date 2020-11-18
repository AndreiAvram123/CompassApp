package com.andrei.dataLayer.engine.repositories

import androidx.lifecycle.MutableLiveData
import com.andrei.dataLayer.engine.core.CallRunner
import com.andrei.dataLayer.engine.core.ResponseHandler
import com.andrei.dataLayer.engine.core.Result
import retrofit2.Call
import retrofit2.Response
import javax.xml.transform.sax.TemplatesHandler

class AuthRepository {

    sealed class AuthenticationResult{
        data class FailedAuthentication(val error:String) : AuthenticationResult()
        object AuthenticationSuccessful :AuthenticationResult()
        object Loading:AuthenticationResult()
    }

    val callRunner = CallRunner(ResponseHandler.getInstance())



    suspend fun performLogin(state:MutableLiveData<AuthenticationResult>) {
        state.postValue(AuthenticationResult.Loading)
        val response =  callRunner.makeCall<Any>(Call<TemplatesHandler>()){
            state.postValue(AuthenticationResult.AuthenticationSuccessful)
        }
        if(response is Result.Error){
            state.postValue(AuthenticationResult.FailedAuthentication(response.exception.localizedMessage ?: "Unknown"))
        }
    }
}