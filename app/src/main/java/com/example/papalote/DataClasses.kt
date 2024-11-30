package com.example.papalote

import kotlinx.serialization.Serializable
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

@Serializable
data class TriviaQuestion(
    val id_pregunta: Int,
    val texto_pregunta: String,
    val opcion_1: String,
    val opcion_2: String,
    val opcion_3: String,
    val respuesta_correcta: Int
)

data class TriviaQuestionsByZoneRequest(
    val nombre_zona: String
)

data class TriviaAnswerRequest(
    val id_pregunta: Int,
    val opcion_seleccionada: Int
)

data class TriviaAnswerResponse(
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
    val fecha_nacimiento: Date, // Asegúrate de manejar este tipo correctamente
    val visitas: List<visitas>
)

data class visitas(
    val nombre_exposicion: String,
    val fecha_hora_visita: Date
)

data class refreshAccessTokenResponse(
    val accessToken: String
)


data class zoneRequest(
    val nombre_zona: String,
)

data class zoneResponse(
    val total_exposiciones_activas: Int,
    val exposiciones_visitadas: Int,
    val porcentaje_avance: Int,
    val total_visitas_unicas: Int,
    val visitas: List<visitas>
)