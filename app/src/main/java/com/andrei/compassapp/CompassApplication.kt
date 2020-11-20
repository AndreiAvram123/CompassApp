package com.andrei.compassapp

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.AmplifyConfiguration

class CompassApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Amplify.addPlugin(AWSCognitoAuthPlugin())
        val config = AmplifyConfiguration.builder(applicationContext).devMenuEnabled(false)
            .build()
        try {
            Amplify.configure(config,applicationContext)
            Log.i("MyAmplifyApp", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
        }

    }
}