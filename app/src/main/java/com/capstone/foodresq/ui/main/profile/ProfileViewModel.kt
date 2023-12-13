package com.capstone.foodresq.ui.main.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.foodresq.data.remote.response.ProfileData
import com.capstone.foodresq.data.repository.ProfileRepository
import com.capstone.foodresq.data.repository.ProfileResult
import com.capstone.foodresq.data.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: UserRepository,
    private val profileRepository: ProfileRepository
): ViewModel() {
    val loading = MutableLiveData<Boolean>().apply { value = false }
    fun logout(){
        viewModelScope.launch {
            userRepository.logout()
        }
    }

    fun profile(): MutableLiveData<ProfileData?> {
        val profileDataLiveData = MutableLiveData<ProfileData?>()
        viewModelScope.launch {
            loading.value = true
            val result = profileRepository.profile()
            try {
                when (result) {
                    is ProfileResult.Success -> {
                        profileDataLiveData.value = result.response.data
                    }
                    is ProfileResult.Failure -> {
                        profileDataLiveData.value = null
                    }
                    is ProfileResult.NetworkError -> {
                        profileDataLiveData.value = null
                    }
                }
            } catch (e: Exception) {
                profileDataLiveData.value = null
            }
            loading.value = false
        }
        return profileDataLiveData
    }

}