package com.andrei.compassapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import com.andrei.compassapp.fragments.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Amplify.Auth.signUp(
            "username",
            "Password123",
            AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), "andreia@apadmi.com").build(),
            { result -> Log.i("AuthQuickStart", "Result: $result") },
            { error -> Log.e("AuthQuickStart", "Sign up failed", error) }
        )
    }
}