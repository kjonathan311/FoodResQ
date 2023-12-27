package com.capstone.foodresq.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.data.remote.response.OrderResponse
import com.capstone.foodresq.data.remote.response.Restaurant
import com.capstone.foodresq.data.repository.DetailRepository
import com.capstone.foodresq.data.repository.GetDetailRestaurantRepository
import com.capstone.foodresq.data.repository.OrderResult
import kotlinx.coroutines.launch

class DetailViewModel(
    private val detailRepository: DetailRepository,
    private val detailRestaurantRepository: GetDetailRestaurantRepository
):ViewModel() {
    val loading = MutableLiveData<Boolean>().apply { value = true }
    val foodData= MutableLiveData<FoodItem?>()
    val restaurant = MutableLiveData<Restaurant?>()

    private val _successResult = MutableLiveData<OrderResponse?>()
    val successResult : LiveData<OrderResponse?> get() = _successResult

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage : LiveData<String?> get() = _errorMessage

    fun getDetail(id:String) {
        viewModelScope.launch {
            try {
                loading.value = true
                val food = detailRepository.getDetailFood(id)
                foodData.value = food
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                loading.value = false
            }
        }
    }

    fun getDetailRestaurant(restaurant_id: String){
        viewModelScope.launch {
            try {
                val restaurantData = detailRestaurantRepository.getDetailRestaurant(restaurant_id)
                restaurant.value = restaurantData
            } catch (e : Exception){
                e.printStackTrace()
            } finally {

            }
        }
    }

    fun orderFood(restaurant_id:String, food_id:String, quantity:Int){
        viewModelScope.launch {
            try {
                val result = detailRepository.orderFood(restaurant_id, food_id, quantity)
                when (result){
                    is OrderResult.Success -> {
                        val response = result.response
                        _successResult.value = response
                        _successResult.value = null
                    }
                    is OrderResult.Failed -> {
                        val errorBody = result.error
                        _errorMessage.value = errorBody.message
                        _errorMessage.value = null
                    }
                    is OrderResult.NetworkError -> {
                        _errorMessage.value = "Network Error"
                        _errorMessage.value = null
                    }
                }
            } catch (e : Exception){
                _errorMessage.value = e.message.toString()
                _errorMessage.value = null
            }
        }
    }
}
