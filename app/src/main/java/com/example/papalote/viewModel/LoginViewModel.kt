package com.example.papalote.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papalote.api.Repository
import com.example.papalote.LoginRequest
import com.example.papalote.states.LoginState
import com.example.papalote.utils.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

import androidx.lifecycle.ViewModelProvider


class LoginViewModel(private val repository: Repository) : ViewModel() { // Cambiamos para aceptar Repository
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: MutableStateFlow<LoginState> = _loginState

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            val request = LoginRequest(
                email = email,
                password = password
            )

            repository.loginUser(request).fold(
                onSuccess = { response ->
                    repository.saveToken(response.accessToken) // Usamos el método público de Repository
                    _loginState.value = LoginState.Success(response.accessToken)
                },
                onFailure = { exception ->
                    _loginState.value = LoginState.Error(exception.message ?: "Unknown error occurred")
                }
            )
        }
    }
}





