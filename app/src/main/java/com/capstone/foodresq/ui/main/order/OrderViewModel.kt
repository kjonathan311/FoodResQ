package com.capstone.foodresq.ui.main.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.foodresq.data.classes.Order
import com.capstone.foodresq.data.repository.GetOrderRepository
import kotlinx.coroutines.launch

class OrderViewModel (
    private val repository: GetOrderRepository
): ViewModel() {
    val allOrder = MutableLiveData<List<Order>?>()

    fun getAllOrder(){
        viewModelScope.launch {
            try {
                val order = repository.getAllOrder()
                allOrder.value = order
            } catch (e : Exception){
                e.printStackTrace()
            } finally {

            }
        }
    }
}