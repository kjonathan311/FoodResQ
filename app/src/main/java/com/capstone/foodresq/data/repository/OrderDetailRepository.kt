package com.capstone.foodresq.data.repository

import android.util.Log
import com.capstone.foodresq.data.remote.response.OrderDetail
import com.capstone.foodresq.data.remote.retrofit.ApiService
import retrofit2.HttpException

class OrderDetailRepository(private val apiService: ApiService) {
    suspend fun getOrderDetail(orderId:String):List<OrderDetail>?{
        try {
            val response = apiService.getOrderDetail(orderId)
            return response.data
        } catch (e:HttpException){
            Log.e("OrderDetailRepository", "Error : ${e.message()}")
            return null
        }
    }
}