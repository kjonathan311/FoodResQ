package com.capstone.foodresq.data.repository

import android.util.Log
import com.capstone.foodresq.data.classes.Order
import com.capstone.foodresq.data.remote.retrofit.ApiService
import retrofit2.HttpException

class GetOrderRepository(private val apiService: ApiService) {
    suspend fun getAllOrder():List<Order>?{
        try {
            val response = apiService.getOrder()
            return response.data
        } catch (e : HttpException){
            Log.e("GetOrderRepository", "Error : ${e.message()}")
            return null
        }
    }

    suspend fun getOrderHistory():List<Order>?{
        try {
            val response = apiService.getOrderHistory()
            return response.data
        } catch (e : HttpException){
            Log.e("GetOrderRepository", "Error : ${e.message()}")
            return null
        }
    }
}