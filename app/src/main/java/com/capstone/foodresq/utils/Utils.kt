package com.capstone.foodresq.utils

import android.text.TextUtils
import android.util.Patterns

object Utils {

    fun isValidEmail(email : String) : Boolean{
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password : String) : Boolean{
        return !TextUtils.isEmpty(password) && password.length >= 8
    }

}