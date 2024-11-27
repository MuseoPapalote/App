package com.example.papalote.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papalote.api.Repository
import com.example.papalote.EncuestaRequest
import com.example.papalote.EncuestaResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EncuestaViewModel(private val repository: Repository) : ViewModel() {

    private val _encuestaState = MutableStateFlow<Result<EncuestaResponse>?>(null)
    val encuestaState: StateFlow<Result<EncuestaResponse>?> = _encuestaState

    fun crearEncuesta(request: EncuestaRequest) {
        viewModelScope.launch {
            val result = repository.crearEncuesta(request)
            _encuestaState.value = result
        }
    }
}
