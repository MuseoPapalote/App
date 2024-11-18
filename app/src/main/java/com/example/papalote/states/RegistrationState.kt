package com.example.papalote.states


sealed class RegistrationState {
    data object Idle : RegistrationState()
    data object Loading : RegistrationState()
    data class Success(val message: String) : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}
