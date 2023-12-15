package com.capstone.foodresq.data.repository

import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.data.remote.retrofit.ApiService
import retrofit2.HttpException

class DetailRepository(private val apiService: ApiService) {

    suspend fun getDetailFood(id:String):FoodItem?{
        try {
            val response=apiService.getFoodDetail(id)
            return response.data?.first()
        }catch (e:HttpException){
            return null
        }
    }

}