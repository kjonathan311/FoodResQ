package com.capstone.foodresq.data.remote.response

import com.google.gson.annotations.SerializedName

data class SubscriptionResponse (
    @field:SerializedName("message")
    val message:String?=null
    )

data class GetSubscriptionResponse (
    @field:SerializedName("message")
    val message:String?=null,
    @field:SerializedName("data")
    val data:subsriptionData?=null
)

data class subsriptionData(
    val membership_type:String
)