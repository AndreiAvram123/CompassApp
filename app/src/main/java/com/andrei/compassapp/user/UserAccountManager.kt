package com.andrei.compassapp.user

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.andrei.dataLayer.engine.repositories.SessionSettingsRepositoryImpl
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UserAccountManager @Inject constructor(private val sharedPreferences: SharedPreferences,
                                             @ApplicationContext private val context: Context){

    val loginState = MutableLiveData<SessionSettingsRepositoryImpl.UserState>()



}