package com.capstone.foodresq.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (

    @field:SerializedName("message")
    val message:String?=null,

    @field:SerializedName("token")
    val token:String?=null
)

data class LoginFailedResponse(
    @field:SerializedName("message")
    val message:String?=null
)