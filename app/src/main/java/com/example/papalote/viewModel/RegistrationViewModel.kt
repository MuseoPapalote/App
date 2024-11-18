package com.example.papalote.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papalote.RegisterRequest
import com.example.papalote.api.Repository
import com.example.papalote.states.RegistrationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class RegistrationViewModel(private val repository: Repository) : ViewModel() { // Cambiamos para aceptar Repository

    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Idle)
    val registrationState: StateFlow<RegistrationState> = _registrationState

    fun registerUser(email: String, birthDate: Date, username: String, password: String) {
        viewModelScope.launch {
            _registrationState.value = RegistrationState.Loading

            val request = RegisterRequest(
                email = email,
                fecha_nacimiento = birthDate,
                nombre = username,
                password = password
            )

            repository.registerUser(request).fold(
                onSuccess = { response ->
                    _registrationState.value = RegistrationState.Success(response.accessToken)
                },
                onFailure = { exception ->
                    _registrationState.value =
                        RegistrationState.Error(exception.message ?: "Unknown error occurred")
                }
            )
        }
    }
}