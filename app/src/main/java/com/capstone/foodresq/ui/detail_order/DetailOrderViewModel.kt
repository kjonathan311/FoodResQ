package com.capstone.foodresq.ui.detail_order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.data.remote.response.OrderDetail
import com.capstone.foodresq.data.remote.response.Restaurant
import com.capstone.foodresq.data.repository.DetailRepository
import com.capstone.foodresq.data.repository.GetDetailRestaurantRepository
import com.capstone.foodresq.data.repository.OrderDetailRepository
import kotlinx.coroutines.launch

class DetailOrderViewModel(
    private val orderRepository : OrderDetailRepository,
    private val foodRepository : DetailRepository,
    private val restaurantDetailRepository: GetDetailRestaurantRepository
) : ViewModel() {

    val foodDetail = MutableLiveData<FoodItem?>()
    val detailOrder = MutableLiveData<List<OrderDetail>?>()
    val restaurant = MutableLiveData<Restaurant?>()

    fun getRestaurantId(foodId:String) {
        viewModelScope.launch {
            try {
                val food = foodRepository.getDetailFood(foodId)
                foodDetail.value = food
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {

            }
        }
    }

    fun getDetailRestaurant(restaurantId:String){
        viewModelScope.launch {
            try {
                val restaurantData = restaurantDetailRepository.getDetailRestaurant(restaurantId)
                restaurant.value = restaurantData
            } catch (e : Exception){
                e.printStackTrace()
            } finally {

            }
        }
    }

    fun getDetailOrder(orderId:String){
        viewModelScope.launch {
            try {
                val order = orderRepository.getOrderDetail(orderId)
                detailOrder.value = order
            } catch (e : Exception){
                e.printStackTrace()
            } finally {

            }
        }
    }
}