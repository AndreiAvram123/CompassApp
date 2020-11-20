package com.andrei.dataLayer.engine.repositories

import androidx.lifecycle.MutableLiveData
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import com.andrei.compassapp.user.UserAccountManager
import com.andrei.dataLayer.engine.core.CallRunner
import com.andrei.dataLayer.engine.core.ResponseHandler
import javax.inject.Inject

class AuthRepository  @Inject constructor(
    private val userAccountManager: UserAccountManager,
    private val sessionSettingsRepository: SessionSettingsRepository){

    private val responseHandler = ResponseHandler.getInstance()

    sealed class AuthenticationResult{
        data class FailedAuthentication(val error:String) : AuthenticationResult()
        object AuthenticationSuccessful :AuthenticationResult()
        object Loading:AuthenticationResult()
    }
    sealed class RegistrationResult{
        data class FailedRegistration(val error:String) : RegistrationResult()
        data class RegistrationSuccessful(val username: String) :RegistrationResult()
        object Loading:RegistrationResult()
    }

    sealed class ConfirmationCodeResult{
        object FailedConfirmation : ConfirmationCodeResult()
        object ConfirmationSuccessful :ConfirmationCodeResult()
        object Loading:ConfirmationCodeResult()
    }
    sealed class UserState{
        object LoggedIn :UserState()
        object NotLoggedIn:UserState()
    }


    val callRunner = CallRunner(ResponseHandler.getInstance())



    suspend fun performLogin(state:MutableLiveData<AuthenticationResult>) {
        state.postValue(AuthenticationResult.Loading)
//        if(response is Result.Error){
//            state.postValue(AuthenticationResult.FailedAuthentication(response.exception.localizedMessage ?: "Unknown"))
//        }
    }

    fun confirmAccount(state:MutableLiveData<ConfirmationCodeResult>, code:String, username: String){
        state.value = ConfirmationCodeResult.Loading
        Amplify.Auth.confirmSignUp(
            username,
            code,
            {
                if(it.isSignUpComplete){
                    state.postValue(ConfirmationCodeResult.ConfirmationSuccessful)

                    userAccountManager.loginState.postValue(UserState.LoggedIn)

                }
            },
            {
               state.postValue(ConfirmationCodeResult.FailedConfirmation)
            }
        )
    }

    fun performRegister(state: MutableLiveData<RegistrationResult>,username:String, email:String,password:String ){
        state.value = RegistrationResult.Loading
        Amplify.Auth.signUp(
            username,
            password,
            AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), email).build(),
            {
                state.postValue( RegistrationResult.RegistrationSuccessful(username))
            },
            {
                state.postValue(RegistrationResult.FailedRegistration(it.message ?: "Unknown error"))
               responseHandler.handleRequestException<Any>(it,"Registration with Amplify")
            }
        )
    }
}