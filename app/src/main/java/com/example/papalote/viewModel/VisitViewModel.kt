package com.example.papalote.viewModel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papalote.VisitRequest
import com.example.papalote.api.Repository
import com.example.papalote.states.VisitState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VisitViewModel(private val repository: Repository): ViewModel() {
    private val _visitState = MutableStateFlow<VisitState>(VisitState.Idle)
    val visitState: StateFlow<VisitState> = _visitState

    fun registerVisit(contenido_qr: String){
        viewModelScope.launch{
            _visitState.value = VisitState.Loading

            val request = VisitRequest(
                contenido_qr
            )

            repository.registerVisit(request).fold(
                onSuccess = { response ->
                    _visitState.value = VisitState.Success("Bien")
                },
                onFailure = {exception ->
                    _visitState.value = VisitState.Error(exception.message ?: "Unkown error ocurred")
                }
            )

        }
    }

}