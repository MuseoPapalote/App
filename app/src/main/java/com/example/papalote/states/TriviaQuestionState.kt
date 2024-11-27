package com.example.papalote.states
import com.example.papalote.TriviaQuestion

sealed class TriviaQuestionState {
    object Idle : TriviaQuestionState()
    object Loading : TriviaQuestionState()
    data class Success(val preguntas: List<TriviaQuestion>) : TriviaQuestionState()
    data class Error(val message: String) : TriviaQuestionState()
}


