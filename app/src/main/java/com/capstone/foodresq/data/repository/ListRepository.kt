package com.capstone.foodresq.data.repository

import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.data.remote.retrofit.ApiService
import retrofit2.HttpException

class ListRepository(private val apiService: ApiService) {
    suspend fun getFoodsByQuery(foodName:String):List<FoodItem>?{
        try {
            val response=apiService.getFoodByQuery(foodName)
            return response.data
        }catch (e: HttpException){
            return null
        }
    }
}