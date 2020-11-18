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

  fun beginLoginAttempt(username:String, password:String){
       viewModelScope.launch {  repo.performLogin(authenticationState)}
  }
}