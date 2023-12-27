package com.capstone.foodresq.data.remote.retrofit

import com.capstone.foodresq.data.remote.response.DetailFoodResponse
import com.capstone.foodresq.data.remote.response.FoodsResponse
import com.capstone.foodresq.data.remote.response.GetOrderDetailResponse
import com.capstone.foodresq.data.remote.response.GetOrderResponse
import com.capstone.foodresq.data.remote.response.GetSubscriptionResponse
import com.capstone.foodresq.data.remote.response.LoginResponse
import com.capstone.foodresq.data.remote.response.OrderResponse
import com.capstone.foodresq.data.remote.response.ProfileResponse
import com.capstone.foodresq.data.remote.response.RegisterResponse
import com.capstone.foodresq.data.remote.response.RestaurantDetailResponse
import com.capstone.foodresq.data.remote.response.SubscriptionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @POST("register")
    suspend fun register(@Body body: registerBody?): RegisterResponse

    @POST("login")
    suspend fun login(@Body body: loginBody?):LoginResponse

    @GET("profile/me")
    suspend fun profile():ProfileResponse

    @GET("foods")
    suspend fun getAllfoods():FoodsResponse

    @GET("foods/recommendation")
    suspend fun getFoodRecommendation():FoodsResponse

    @GET("foods/popular")
    suspend fun getFoodPopular():FoodsResponse

    @POST("order")
    suspend fun order(@Body body: orderBody?):OrderResponse

    @GET("orders")
    suspend fun getOrder():GetOrderResponse

    @GET("order/history")
    suspend fun getOrderHistory():GetOrderResponse

    @GET("orders/{orderId}")
    suspend fun getOrderDetail(@Path("orderId") orderId:String):GetOrderDetailResponse

    @GET("foods/{foodId}")
    suspend fun getFoodDetail(@Path("foodId") foodId: String): DetailFoodResponse

    @GET("restaurants/{restaurantId}")
    suspend fun getRestaurantDetail(@Path("restaurantId") restaurant_id: String):RestaurantDetailResponse

    @GET("foods")
    suspend fun getFoodByQuery(@Query("name") foodName: String): FoodsResponse

    @POST("subscribe")
    suspend fun subscribe():SubscriptionResponse

    @GET("subscription")
    suspend fun getSubscription():GetSubscriptionResponse

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
data class orderBody(
    val restaurant_id : String,
    val food_id : String,
    val quantity : Int
)