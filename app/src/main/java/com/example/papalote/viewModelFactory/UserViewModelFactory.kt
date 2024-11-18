package com.example.papalote.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.papalote.RetrofitClient
import com.example.papalote.api.Repository
import com.example.papalote.utils.TokenManager
import com.example.papalote.viewModel.UserViewModel

class UserViewModelFactory(private val tokenManager: TokenManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            // Proporcionamos apiService y tokenManager al Repository
            val apiService = RetrofitClient.apiService
            val repository = Repository(apiService, tokenManager)
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
