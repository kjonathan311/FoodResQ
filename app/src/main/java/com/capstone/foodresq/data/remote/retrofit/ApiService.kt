package com.capstone.foodresq.data.remote.retrofit

import com.capstone.foodresq.data.remote.response.LoginResponse
import com.capstone.foodresq.data.remote.response.ProfileResponse
import com.capstone.foodresq.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @POST("register")
    suspend fun register(@Body body: registerBody?): RegisterResponse

    @POST("login")
    suspend fun login(@Body body: loginBody?):LoginResponse

    @GET("profile/me")
    suspend fun profile():ProfileResponse

}
data class registerBody(
    val name: String,
    val email: String,
    val password: String
)
data class loginBody(
    val email: String,
    val password: String
)