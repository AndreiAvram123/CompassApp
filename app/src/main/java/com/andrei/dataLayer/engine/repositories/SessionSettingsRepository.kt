package com.andrei.dataLayer.engine.repositories

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.andrei.compassapp.user.UserAccountManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

interface SessionSettingsRepository {
    var accessToken: String?
    var tokenType: String?
    var refreshToken: String?
    var userID :String?
}

@Singleton
class SessionSettingsRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
    userAccountManager: UserAccountManager): BaseSettingsRepository(context), SessionSettingsRepository {


    companion object {
         private const val AUTH_TOKEN_KEY = "auth_token_key"
         private const val REFRESH_TOKEN_KEY = "refresh_token_key"
         private const val TOKEN_TYPE_KEY = "token_type_key"
         private const val USER_ID = "user_id"
    }

    override var tokenType: String? by EncryptedStringDelegate(TOKEN_TYPE_KEY)
    override var accessToken: String? by EncryptedStringDelegate(AUTH_TOKEN_KEY)
    override var refreshToken: String? by EncryptedStringDelegate(REFRESH_TOKEN_KEY)
    override var userID: String? by EncryptedStringDelegate(REFRESH_TOKEN_KEY)


    init {
        userAccountManager.loginState.observeForever{
            if(it is UserState.NotLoggedIn){
                clearAll()
            }
        }
    }


    private fun clearAll() {
        accessToken = null
        refreshToken = null
        tokenType = null
        userID = null
    }
}