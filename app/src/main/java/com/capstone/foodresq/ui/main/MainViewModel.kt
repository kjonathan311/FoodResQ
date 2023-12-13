package com.capstone.foodresq.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.foodresq.data.datastore.UserModel
import com.capstone.foodresq.data.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepository: UserRepository
):ViewModel() {
    val loading = MutableLiveData<Boolean>().apply { value = true }

    fun getSession(): LiveData<UserModel> {
        return userRepository.getSession().asLiveData()
    }
}