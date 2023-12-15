package com.capstone.foodresq.data.remote.retrofit

import com.capstone.foodresq.data.remote.response.DetailFoodResponse
import com.capstone.foodresq.data.remote.response.FoodsResponse
import com.capstone.foodresq.data.remote.response.LoginResponse
import com.capstone.foodresq.data.remote.response.ProfileResponse
import com.capstone.foodresq.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {

    @POST("register")
    suspend fun register(@Body body: registerBody?): RegisterResponse

    @POST("login")
    suspend fun login(@Body body: loginBody?):LoginResponse

    @GET("profile/me")
    suspend fun profile():ProfileResponse

    @GET("foods")
    suspend fun getAllfoods():FoodsResponse

    @GET("foods/{foodId}")
    suspend fun getFoodDetail(@Path("foodId") foodId: String): DetailFoodResponse

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