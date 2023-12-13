package com.capstone.foodresq.data.repository

import com.capstone.foodresq.data.remote.response.ProfileResponse
import com.capstone.foodresq.data.remote.retrofit.ApiService
import retrofit2.HttpException
import java.io.IOException

class ProfileRepository(
    private val apiService: ApiService
) {
    suspend fun profile():ProfileResult{
        try {
            val response = apiService.profile()
            return ProfileResult.Success(response)
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            return ProfileResult.Failure(jsonInString.toString())
        } catch (e: IOException) {
            return ProfileResult.NetworkError
        }
    }
}

sealed class ProfileResult {
    data class Success(val response: ProfileResponse) : ProfileResult()
    data class Failure(val error:String) : ProfileResult()
    object NetworkError : ProfileResult()
}