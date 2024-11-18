package com.example.papalote

import java.util.Date


data class RegisterRequest(
    val email: String,
    val fecha_nacimiento: Date,
    val nombre: String,
    val password: String
)

data class RegisterResponse(
    val accessToken: String,
    val refreshToken: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)

data class TriviaAnswerRequest(
    val id_pregunta: Int,
    val opcion_seleccionada: Int
)

data class TriviaAnswerResponse(
    val id_respuesta: Int,
    val id_usuario: Int,
    val id_pregunta: Int,
    val opcion_seleccionada: Int,
    val es_correcta: Boolean
)

data class TriviaAnswersListType(
    val id_respuesta: Int,
    val id_pregunta: Int,
    val opcion_seleccionada: Int,
    val es_correcta: Boolean,
    val created_at: Date
)

data class TriviaAnswersResponse(
    val respuestas: List<TriviaAnswersListType>
)

data class VisitRequest(
    val contenido_qr: String
)

data class VisitResponse(
    val id_visita: Int,
    val id_usuario: Int,
    val id_exposicion: Int,
    val fecha_hora_visita: Date
)

data class ProgresoResponseType(
    val id_zona: Int,
    val nombre_zona: String,
    val exposiciones_visitadas: Int,
    val total_exposiciones: Int,
    val porcentaje_avance: Int
)

data class ProgresoResponse(
    val zonas: List<ProgresoResponseType>
)

data class EncuestaRequest(
    val calificacion_general: Int,
    val comentarios: String
)

data class EncuestaResponse(
    val id_encuesta: Int,
    val id_usuario: Int,
    val calificacion_general: Int,
    val comentarios: String,
    val fecha_encuesta: Date,
    val respondido: Boolean = false
)

data class UserResponse(
    val nombre: String,
    val email: String,
    val fecha_nacimiento: Date // Asegúrate de manejar este tipo correctamente
)