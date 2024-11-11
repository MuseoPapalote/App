package com.example.papalote.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papalote.API.Repository
import com.example.papalote.LoginRequest
import com.example.papalote.states.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){
    private val repository = Repository()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: MutableStateFlow<LoginState> = _loginState

    fun loginUser(email: String, password: String){
        viewModelScope.launch{
            _loginState.value = LoginState.Loading

            val request = LoginRequest(
                email = email,
                password = password
            )

            repository.loginUser(request).fold(
                onSuccess = { response ->
                    _loginState.value = LoginState.Success(response.accessToken)
                },
                onFailure = { exception ->
                    _loginState.value = LoginState.Error(exception.message ?: "Unknown error occurred")
                }
            )
        }
    }
}