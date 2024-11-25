package com.example.papalote.states

sealed class VisitState {
    data object Idle: VisitState()
    data object Loading: VisitState()
    data class Success(val message: String): VisitState()
    data class Error(val message: String): VisitState()
}