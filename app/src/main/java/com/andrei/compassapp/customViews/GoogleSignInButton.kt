package com.andrei.compassapp.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.andrei.compassapp.R

class GoogleSignInButton: FrameLayout {
    constructor(context:Context) : super(context){
        initViews()
    }
    constructor(context: Context,attributeSet: AttributeSet?) : super(context,attributeSet){
        initViews()
    }
    constructor(context: Context,attributeSet: AttributeSet?,defStyleAttr:Int):
            super(context,attributeSet,defStyleAttr){
        initViews()
    }

    private fun initViews(){
        val view = LayoutInflater.from(context).inflate(R.layout.view_google_sign_in_button,this)

    }

}