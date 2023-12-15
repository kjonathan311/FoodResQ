package com.capstone.foodresq.data.remote.response

import com.capstone.foodresq.data.classes.FoodItem
import com.google.gson.annotations.SerializedName

data class FoodsResponse(
    @field:SerializedName("message")
    val message:String?=null,

    @field:SerializedName("data")
    val data:List<FoodItem>?=null
)
