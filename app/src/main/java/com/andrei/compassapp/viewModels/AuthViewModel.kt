package com.andrei.compassapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrei.dataLayer.engine.repositories.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    val repo = AuthRepository()
   val authenticationState: MutableLiveData<AuthRepository.AuthenticationResult> by lazy {
      MutableLiveData()
   }
    val registrationState: MutableLiveData<AuthRepository.RegistrationResult> by lazy {
        MutableLiveData()
    }
    val validationCodeState:MutableLiveData<AuthRepository.ConfirmationCodeResult> by lazy {
        MutableLiveData()
    }

  fun beginLoginAttempt(username:String, password:String){
       viewModelScope.launch {  repo.performLogin(authenticationState)}
  }

    fun beginRegistration(username:String, email:String,password:String){
        repo.performRegister(
            state =  registrationState,
            username = username,
            email = email,
            password = password
        )
    }
    fun confirmAccount(code :String,username: String){
        repo.confirmAccount(validationCodeState,code = code, username = username)
    }
}