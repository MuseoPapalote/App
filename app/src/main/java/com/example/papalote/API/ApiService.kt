package com.example.papalote.api
import com.example.papalote.LoginRequest
import com.example.papalote.LoginResponse
import com.example.papalote.RegisterRequest
import com.example.papalote.RegisterResponse
import com.example.papalote.UserResponse
import com.example.papalote.TriviaAnswerRequest
import com.example.papalote.TriviaAnswerResponse
import com.example.papalote.TriviaAnswersResponse
import com.example.papalote.TriviaQuestion
import com.example.papalote.TriviaQuestionsByZoneRequest

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @POST("/usuarios/register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("/usuarios/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginResponse>

    @GET("/usuarios/profile")
    suspend fun getUserInfo(
        @Header("Authorization") token: String
    ): Response<UserResponse>

    // Actualización con los nuevos nombres
    @POST("/preguntaTrivia")
    suspend fun obtenerPreguntasPorZona(
        @Header("Authorization") token: String,
        @Body zonaRequest: TriviaQuestionsByZoneRequest
    ): Response<List<TriviaQuestion>>

    @POST("/respuestaTrivia")
    suspend fun enviarTriviaAnswer(
        @Header("Authorization") token: String,
        @Body triviaAnswerRequest: TriviaAnswerRequest
    ): Response<TriviaAnswerResponse>

    @GET("/respuestaTrivia")
    suspend fun obtenerTriviaAnswers(): TriviaAnswersResponse

}

