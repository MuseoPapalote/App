package com.example.papalote.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.papalote.api.Repository
import com.example.papalote.viewModel.RegistrationViewModel

class RegistrationViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}