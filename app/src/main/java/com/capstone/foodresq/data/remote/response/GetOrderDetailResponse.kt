package com.capstone.foodresq.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetOrderDetailResponse(

	@field:SerializedName("“message”")
	val message: String,

	@field:SerializedName("data")
	val data: List<OrderDetail>?=null
)

data class OrderDetail(

	@field:SerializedName("quantity")
	val quantity: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("food_id")
	val foodId: String,

	@field:SerializedName("order_id")
	val orderId: String
)

data class GetOrderDetailFailedResponse(

	@field:SerializedName("message")
	val message: String

)
