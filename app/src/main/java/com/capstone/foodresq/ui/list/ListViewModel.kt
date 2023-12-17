package com.capstone.foodresq.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.data.repository.ExploreRepository
import com.capstone.foodresq.data.repository.ListRepository
import kotlinx.coroutines.launch

class ListViewModel(
    private val listRepository: ListRepository
): ViewModel() {
    val loading = MutableLiveData<Boolean>().apply { value = true }
    val queryFoodsData = MutableLiveData<List<FoodItem>?>()
    fun getFoodsByQuery(foodName:String) {
        viewModelScope.launch {
            try {
                loading.value = true
                val foods = listRepository.getFoodsByQuery(foodName)
                queryFoodsData.value = foods
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                loading.value = false
            }
        }
    }
}