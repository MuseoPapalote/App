package com.example.papalote.states

import com.example.papalote.UserResponse

sealed class UserState {
    object Idle : UserState()
    object Loading : UserState()
    data class Success(val user: UserResponse) : UserState()
    data class Error(val message: String) : UserState()
}
