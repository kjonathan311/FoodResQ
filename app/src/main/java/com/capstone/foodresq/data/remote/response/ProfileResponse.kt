package com.capstone.foodresq.data.remote.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @field:SerializedName("message")
    val message:String?=null,

    @field:SerializedName("data")
    val data:ProfileData?=null
)

data class ProfileData(
    val email:String,
    val name:String,
    val membership_type:String
)