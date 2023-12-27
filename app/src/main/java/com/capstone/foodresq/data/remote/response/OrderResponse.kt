package com.capstone.foodresq.data.remote.response

import com.google.gson.annotations.SerializedName
data class OrderResponse(

	@field:SerializedName("“message”")
	val message: String,

	@field:SerializedName("“data”")
	val data: Data
)

data class Data(

	@field:SerializedName("“id”")
	val id: String
)

data class OrderFailedResponse(

	@field:SerializedName("“message“")
	val message: String

)
