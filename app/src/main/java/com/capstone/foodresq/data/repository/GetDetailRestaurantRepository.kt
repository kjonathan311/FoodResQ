package com.capstone.foodresq.data.repository

import android.util.Log
import com.capstone.foodresq.data.remote.response.Restaurant
import com.capstone.foodresq.data.remote.retrofit.ApiService
import retrofit2.HttpException

class GetDetailRestaurantRepository(
    private val apiService: ApiService
) {
   suspend fun getDetailRestaurant(restaurantId:String) : Restaurant?{
       try {
           val response = apiService.getRestaurantDetail(restaurantId)
           return response.data
       } catch (e : HttpException){
           Log.d("GetDetailRestaurantResponse", "Error : ${e.message()}")
           return null
       }
   }
}