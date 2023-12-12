package com.capstone.foodresq.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.foodresq.data.datastore.UserModel
import com.capstone.foodresq.data.remote.response.LoginResponse
import com.capstone.foodresq.data.repository.LoginRepository
import com.capstone.foodresq.data.repository.LoginResult
import com.capstone.foodresq.data.repository.UserRepository
import kotlinx.coroutines.launch


class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val userRepository: UserRepository
): ViewModel() {

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    private val _successResult = MutableLiveData<LoginResponse?>()
    val successResult:LiveData<LoginResponse?> get() = _successResult

    val loading = MutableLiveData<Boolean>().apply { value = false }

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            userRepository.saveSession(user)
        }
    }

    fun getSession(): LiveData<UserModel> {
        return userRepository.getSession().asLiveData()
    }

    fun login(email:String,password:String){
        viewModelScope.launch {
            loading.value=true
            try {
                val result = loginRepository.login(email, password)
                when (result) {
                    is LoginResult.Success -> {
                        //Handle Success Login
                        val response=result.response
                        _successResult.value=response
                        _successResult.value=null
                    }
                    is LoginResult.Failure -> {
                        // Handle failed login
                        val errorBody = result.errorBody
                        _errorMessage.value=errorBody.message
                        _errorMessage.value=null
                    }
                    is LoginResult.NetworkError -> {
                        // Handle network error
                        _errorMessage.value="Network Error"
                        _errorMessage.value=null
                    }
                }
            } catch (e: Exception) {
                _errorMessage.value=e.message.toString()
                _errorMessage.value=null
            }
            loading.value=false
        }
    }

}