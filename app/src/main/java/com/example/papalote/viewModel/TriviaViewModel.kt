package com.example.papalote.viewModel


import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.papalote.api.Repository
import com.example.papalote.TriviaAnswerRequest
import com.example.papalote.TriviaAnswerResponse
import com.example.papalote.TriviaAnswersResponse
import com.example.papalote.TriviaAnswersListType
import com.example.papalote.states.TriviaAnswerState
//import com.example.papalote.ui.theme.pages.TriviaAnswerRequest
import com.example.papalote.states.TriviaQuestionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import com.example.papalote.utils.TokenManager

import androidx.lifecycle.ViewModelProvider

class TriviaViewModel(private val repository: Repository) : ViewModel() {

    // Estado para manejar la pregunta de trivia
    private val _triviaQuestionState =
        MutableStateFlow<TriviaQuestionState>(TriviaQuestionState.Idle)
    val triviaQuestionState: StateFlow<TriviaQuestionState> = _triviaQuestionState.asStateFlow()

    // Estado para manejar la respuesta de trivia
    private val _triviaAnswerState = MutableStateFlow<TriviaAnswerState>(TriviaAnswerState.Idle)
    val triviaAnswerState: StateFlow<TriviaAnswerState> = _triviaAnswerState.asStateFlow()

    // Obtener una pregunta de trivia
    // Función para obtener preguntas por zona
    fun obtenerPreguntasPorZona(zona: String) {
        viewModelScope.launch {
            _triviaQuestionState.value = TriviaQuestionState.Loading
            val result = repository.obtenerPreguntasPorZona(zona)
            result.fold(
                onSuccess = { preguntas ->
                    if (preguntas.isNotEmpty()) {
                        println("Preguntas obtenidas: $preguntas") // Verifica que se imprimen aquí
                        _triviaQuestionState.value = TriviaQuestionState.Success(preguntas)
                    } else {
                        _triviaQuestionState.value =
                            TriviaQuestionState.Error("No se encontraron preguntas para la zona especificada.")
                    }
                },
                onFailure = { error ->
                    _triviaQuestionState.value = TriviaQuestionState.Error(
                        error.message ?: "Error desconocido al obtener preguntas."
                    )
                }
            )
        }
    }

    fun enviarRespuestaTrivia(idPregunta: Int, opcionSeleccionada: Int) {
        viewModelScope.launch {
            try {
                _triviaAnswerState.value = TriviaAnswerState.Loading
                println("Preparando request con idPregunta: $idPregunta, opcionSeleccionada: $opcionSeleccionada")

                val request = TriviaAnswerRequest(
                    id_pregunta = idPregunta,
                    opcion_seleccionada = opcionSeleccionada
                )

                println("Enviando al repositorio: $request")

                val response = repository.enviarRespuestaTrivia(request)
                println("Enviando al API: $request")

                response.fold(
                    onSuccess = { respuesta ->
                        println("Respuesta del backend: $respuesta")
                        _triviaAnswerState.value = TriviaAnswerState.Success(respuesta.es_correcta)
                    },
                    onFailure = { error ->
                        println("Error al enviar respuesta: ${error.message}")
                        _triviaAnswerState.value =
                            TriviaAnswerState.Error(error.message ?: "Error desconocido")
                    }
                )
            } catch (e: Exception) {
                println("Excepción al enviar respuesta: ${e.message}")
                _triviaAnswerState.value = TriviaAnswerState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}
