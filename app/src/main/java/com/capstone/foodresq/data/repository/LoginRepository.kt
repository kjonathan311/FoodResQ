package com.capstone.foodresq.data.repository

import com.capstone.foodresq.data.remote.response.LoginFailedResponse
import com.capstone.foodresq.data.remote.response.LoginResponse
import com.capstone.foodresq.data.remote.retrofit.ApiService
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

class LoginRepository(private val apiService: ApiService) {

    suspend fun login(email: String, password: String): LoginResult {
        try {
            val response = apiService.login(email, password)
            return LoginResult.Success(response)
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, LoginFailedResponse::class.java)
            return LoginResult.Failure(errorBody)
        } catch (e: IOException) {
            return LoginResult.NetworkError
        }
    }
}

sealed class LoginResult {
    data class Success(val response: LoginResponse) : LoginResult()
    data class Failure(val errorBody: LoginFailedResponse) : LoginResult()
    object NetworkError : LoginResult()
}