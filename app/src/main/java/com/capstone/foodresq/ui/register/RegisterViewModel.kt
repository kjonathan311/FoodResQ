package com.capstone.foodresq.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.foodresq.data.repository.RegisterRepository
import com.capstone.foodresq.data.repository.RegisterResult
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerRepository: RegisterRepository
):ViewModel() {
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    private val _successMessage = MutableLiveData<String?>()
    val successMessage: LiveData<String?> get() = _successMessage

    val loading = MutableLiveData<Boolean>().apply { value = false }

    fun register(name:String,email:String,password:String){
        viewModelScope.launch {
            loading.value=true
            try {
                val result = registerRepository.register(name,email,password)
                when (result) {
                    is RegisterResult.Success -> {
                        //Handle Success Register
                        val response=result.response
                        _successMessage.value=response.message
                        _successMessage.value=null
                    }
                    is RegisterResult.Failure -> {
                        // Handle failed Register
                        val errorBody = result.errorBody
                        _errorMessage.value=errorBody.message
                        _errorMessage.value=null
                    }
                    is RegisterResult.NetworkError -> {
                        // Handle network error
                        _errorMessage.value="Network Error"
                        _errorMessage.value=null
                    }
                }
            }catch (e:Exception){
                _errorMessage.value=e.message.toString()
                _errorMessage.value=null
            }
            loading.value=false
        }
    }
}