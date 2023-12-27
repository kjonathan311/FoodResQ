package com.capstone.foodresq.data.repository

import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.data.remote.response.OrderFailedResponse
import com.capstone.foodresq.data.remote.response.OrderResponse
import com.capstone.foodresq.data.remote.retrofit.ApiService
import com.capstone.foodresq.data.remote.retrofit.orderBody
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

class DetailRepository(private val apiService: ApiService) {

    suspend fun getDetailFood(id:String):FoodItem?{
        try {
            val response=apiService.getFoodDetail(id)
            return response.data?.first()
        }catch  (e:HttpException){
            return null
        }
    }

    suspend fun orderFood(restaurant_id:String, food_id:String, quantity:Int) : OrderResult{
        try {
            val response = apiService.order(orderBody(restaurant_id, food_id, quantity))
            return OrderResult.Success(response)
        } catch (e : HttpException){
            val JSONString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(JSONString, OrderFailedResponse::class.java)
            return OrderResult.Failed(errorBody)
        } catch (e : IOException){
            return OrderResult.NetworkError
        }
    }
}

sealed class OrderResult {
    data class Success(val response: OrderResponse) : OrderResult()
    data class Failed(val error : OrderFailedResponse) : OrderResult()
    object NetworkError : OrderResult()
}