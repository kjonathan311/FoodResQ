package com.capstone.foodresq.data.datastore

data class UserModel (
    val token: String,
    val isLogin: Boolean = false
)