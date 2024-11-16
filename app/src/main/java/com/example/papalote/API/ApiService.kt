package com.example.papalote.API
import com.example.papalote.LoginRequest
import com.example.papalote.LoginResponse
import com.example.papalote.RegisterRequest
import com.example.papalote.RegisterResponse
import com.example.papalote.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET

interface ApiService {
    @POST("/usuarios/register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("/usuarios/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginResponse>

    @GET("/profile")
    suspend fun getUserInfo(): Response<UserResponse>

}

