package com.capstone.foodresq.data.repository

import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.data.remote.retrofit.ApiService
import retrofit2.HttpException

class ExploreRepository(private val apiService: ApiService) {

    suspend fun getAllFoods():List<FoodItem>?{
        try {
            val response=apiService.getAllfoods()
            return response.data
        }catch (e: HttpException){
            return null
        }
    }
}