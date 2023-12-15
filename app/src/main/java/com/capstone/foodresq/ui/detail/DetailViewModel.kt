package com.capstone.foodresq.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.data.repository.DetailRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    private val detailRepository: DetailRepository
):ViewModel() {
    val loading = MutableLiveData<Boolean>().apply { value = true }
    val foodData= MutableLiveData<FoodItem?>()
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
}
