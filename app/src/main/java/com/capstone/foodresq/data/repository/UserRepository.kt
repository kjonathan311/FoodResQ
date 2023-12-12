package com.capstone.foodresq.data.repository

import com.capstone.foodresq.data.datastore.UserModel
import com.capstone.foodresq.data.datastore.UserPreference
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userPreference: UserPreference) {
    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }


}