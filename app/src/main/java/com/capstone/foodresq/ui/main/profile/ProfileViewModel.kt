package com.capstone.foodresq.ui.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.foodresq.data.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    fun logout(){
        viewModelScope.launch {
            userRepository.logout()
        }
    }
}