package com.example.papalote.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papalote.RegisterRequest
import com.example.papalote.API.Repository
import com.example.papalote.states.RegistrationState
import com.example.papalote.states.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class UserViewModel : ViewModel() {
    private val repository = Repository()

    private val _userState = MutableStateFlow<UserState>(UserState.Idle)
    val userState: StateFlow<UserState> = _userState

    fun fetchUserInfo() {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            repository.getUserInfo().fold(
                onSuccess = { user ->
                    _userState.value = UserState.Success(user)
                },
                onFailure = { error ->
                    _userState.value = UserState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }
}
