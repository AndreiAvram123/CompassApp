package com.andrei.compassapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andrei.compassapp.fragments.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.layout_main_activity,LoginFragment()).commit()

    }
}