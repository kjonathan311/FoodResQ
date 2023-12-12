package com.capstone.foodresq.data.datastore

data class UserModel (
    val userId: String,
    val token: String,
    val isLogin: Boolean = false
)