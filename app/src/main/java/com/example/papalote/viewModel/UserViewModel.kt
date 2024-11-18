package com.example.papalote.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papalote.RegisterRequest
import com.example.papalote.api.Repository
import com.example.papalote.states.RegistrationState
import com.example.papalote.states.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import com.example.papalote.utils.TokenManager

import androidx.lifecycle.ViewModelProvider


class UserViewModel(private val repository: Repository) : ViewModel() {
    private val _userState = MutableStateFlow<UserState>(UserState.Idle)
    val userState: StateFlow<UserState> = _userState

    fun fetchUserInfo() {
        // Evitar ejecutar esta función varias veces si ya estamos en Success
        if (_userState.value is UserState.Success) return

        viewModelScope.launch {
            _userState.value = UserState.Loading
            repository.getUserInfo().fold(
                onSuccess = { user ->
                    println("Datos recibidos del usuario: $user")
                    _userState.value = UserState.Success(user)
                },
                onFailure = { error ->
                    println("Error al obtener los datos del usuario: ${error.message}")
                    _userState.value = UserState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }
}

