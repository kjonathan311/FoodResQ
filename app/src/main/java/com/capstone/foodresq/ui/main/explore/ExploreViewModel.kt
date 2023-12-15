package com.capstone.foodresq.ui.main.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.data.remote.response.ProfileData
import com.capstone.foodresq.data.repository.ExploreRepository
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val exploreRepository: ExploreRepository
): ViewModel() {
    val loading = MutableLiveData<Boolean>().apply { value = true }
    val allFoodsData = MutableLiveData<List<FoodItem>?>()
    fun getAllFoods() {
        viewModelScope.launch {
            try {
                loading.value = true
                val foods = exploreRepository.getAllFoods()
                allFoodsData.value = foods
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                loading.value = false
            }
        }
    }
}