package com.capstone.foodresq.ui.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.foodresq.data.remote.response.Restaurant
import com.capstone.foodresq.data.repository.GetDetailRestaurantRepository
import kotlinx.coroutines.launch

class MapViewModel(
    private val detailRestaurantRepository: GetDetailRestaurantRepository
) : ViewModel() {
    val restaurant = MutableLiveData<Restaurant?>()

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
}