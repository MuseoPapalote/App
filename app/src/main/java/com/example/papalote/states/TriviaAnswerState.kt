package com.example.papalote.states

sealed class TriviaAnswerState {
    object Idle : TriviaAnswerState() // Estado inicial
    object Loading : TriviaAnswerState() // Estado de carga
    data class Success(val esCorrecta: Boolean) : TriviaAnswerState() // Respuesta exitosa
    data class Error(val message: String) : TriviaAnswerState() // Error al enviar
}

