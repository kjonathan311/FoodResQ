package com.capstone.foodresq.data.classes

data class FoodItem (
    val name:String,
    val description:String,
    val price:Int,
    val discount_price:Int,
    val quantity:Int,
    val image:String,
    val restaurant_id:String
)