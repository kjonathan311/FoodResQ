package com.capstone.foodresq.ui.subscription

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.foodresq.data.repository.SubscriptionRepository
import kotlinx.coroutines.launch

class SubscriptionViewModel(
    private val subscriptionRepository: SubscriptionRepository
):ViewModel() {
    val loading = MutableLiveData<Boolean>().apply { value = false }

    private val _subscriptionStatus = MutableLiveData<String?>()
    val subscriptionStatus: LiveData<String?> get() = _subscriptionStatus

    private val _subscribeStatus = MutableLiveData<String?>()
    val subscribeStatus: LiveData<String?> get() = _subscribeStatus

    fun getSubscription() {
        viewModelScope.launch {
            val result = subscriptionRepository.getSubscriptionStatus()
            _subscriptionStatus.value = result
        }
    }
    fun subscribe(){
        viewModelScope.launch {
            loading.value=true
            val result=subscriptionRepository.subscribe()
            _subscribeStatus.value=result
            loading.value=false
        }
    }
}