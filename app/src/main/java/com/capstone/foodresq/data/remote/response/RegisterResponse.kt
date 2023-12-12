package com.capstone.foodresq.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse (

    @field:SerializedName("message")
    val message:String?=null

)

data class RegisterFailedResponse(
    @field:SerializedName("message")
    val message:String?=null
)