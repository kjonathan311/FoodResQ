package com.capstone.foodresq.data.remote.response

import com.capstone.foodresq.data.classes.Order
import com.google.gson.annotations.SerializedName
data class GetOrderResponse(

	@field:SerializedName("data")
	val data: List<Order>?=null,

	@field:SerializedName("message")
	val message: String
)