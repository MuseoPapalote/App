package com.example.papalote.api
import com.example.papalote.LoginRequest
import com.example.papalote.LoginResponse
import com.example.papalote.RegisterRequest
import com.example.papalote.RegisterResponse
import com.example.papalote.UserResponse
import com.example.papalote.VisitRequest
import com.example.papalote.VisitResponse
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

    @POST("/visit/register")
    suspend fun registerVisit(
        @Header("Authorization") token: String,
        @Body request: VisitRequest
    ): Response<VisitResponse>

}

