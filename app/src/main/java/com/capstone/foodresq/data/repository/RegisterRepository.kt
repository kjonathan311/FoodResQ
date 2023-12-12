package com.capstone.foodresq.data.repository

import retrofit2.HttpException
import com.capstone.foodresq.data.remote.response.RegisterFailedResponse
import com.capstone.foodresq.data.remote.response.RegisterResponse
import com.capstone.foodresq.data.remote.retrofit.ApiService
import com.capstone.foodresq.data.remote.retrofit.registerBody
import com.google.gson.Gson
import java.io.IOException

class RegisterRepository(private val apiService: ApiService) {

    suspend fun register(name:String,email: String, password: String): RegisterResult {
        try {
            val response = apiService.register(registerBody(name,email,password))
            return RegisterResult.Success(response)
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, RegisterFailedResponse::class.java)
            return RegisterResult.Failure(errorBody)
        } catch (e: IOException) {
            return RegisterResult.NetworkError
        }
    }
}

sealed class RegisterResult {
    data class Success(val response: RegisterResponse) : RegisterResult()
    data class Failure(val errorBody: RegisterFailedResponse) : RegisterResult()
    object NetworkError : RegisterResult()
}