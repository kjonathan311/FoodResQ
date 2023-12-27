package com.capstone.foodresq.data.dummy

data class Restaurant(
    val id : Int,
    val photo : Int,
    val name : String,
    val startTime : String,
    val endTime : String,
    val address : String,
    val rating : Double,
    val lon : Double,
    val lat : Double,
    val listFood : List<Food>
)
