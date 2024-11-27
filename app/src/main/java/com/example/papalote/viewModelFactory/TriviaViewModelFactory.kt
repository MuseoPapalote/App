package com.example.papalote.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.papalote.api.Repository
import com.example.papalote.viewModel.TriviaViewModel

class TriviaViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TriviaViewModel::class.java)) {
            return TriviaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}