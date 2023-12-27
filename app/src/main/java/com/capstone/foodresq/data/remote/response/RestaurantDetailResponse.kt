package com.capstone.foodresq.data.remote.response

import com.capstone.foodresq.data.classes.FoodItem
import com.google.gson.annotations.SerializedName
data class RestaurantDetailResponse(

	@field:SerializedName("data")
	val data: Restaurant,

	@field:SerializedName("message")
	val message: String
)
data class Restaurant(

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("foods")
	val foods: List<FoodItem>,

	@field:SerializedName("latitude")
	val latitude: Any,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: String,

	@field:SerializedName("open_time")
	val openTime: String,

	@field:SerializedName("phone_number")
	val phoneNumber: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("close_time")
	val closeTime: String,

	@field:SerializedName("longitude")
	val longitude: Any
)
