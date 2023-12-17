package com.capstone.foodresq.data.classes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class FoodItem (
    val id:String,
    val name:String,
    val description:String,
    val price:Int,
    val discount_price:Int,
    val quantity:Int,
    val image:String,
    val restaurant_id:String
):Parcelable