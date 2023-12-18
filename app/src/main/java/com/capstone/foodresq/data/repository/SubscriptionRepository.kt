package com.capstone.foodresq.data.repository

import com.capstone.foodresq.data.remote.retrofit.ApiService
import retrofit2.HttpException

class SubscriptionRepository(private val apiService: ApiService) {
    suspend fun subscribe():String?{
        try {
            val response=apiService.subscribe()
            return response.message
        }catch  (e: HttpException){
            return null
        }
    }
    suspend fun getSubscriptionStatus():String?{
        try {
            val response=apiService.getSubscription()
            return response.data?.membership_type
        }catch (e:HttpException){
            return null
        }
    }
}