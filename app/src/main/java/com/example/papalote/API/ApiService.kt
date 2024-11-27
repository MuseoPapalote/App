package com.example.papalote.api
import com.example.papalote.LoginRequest
import com.example.papalote.LoginResponse
import com.example.papalote.RegisterRequest
import com.example.papalote.RegisterResponse
import com.example.papalote.UserResponse
import com.example.papalote.VisitRequest
import com.example.papalote.VisitResponse
import com.example.papalote.refreshAccessTokenResponse
import com.example.papalote.zoneRequest
import com.example.papalote.zoneResponse
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

    @POST("usuarios/verifyRefreshToken")
    suspend fun verifyRefreshToken(
        @Body refreshToken: Map<String, String>
    ): Response<Unit>

    @POST("usuarios/token")
    suspend fun refreshAccessToken(
        @Body refreshToken: Map<String, String>
    ): Response<refreshAccessTokenResponse>

    @POST("progressZone/stats")
    suspend fun getZoneStats(
        @Header("Authorization") token: String,
        @Body request: zoneRequest
    ): Response<zoneResponse>

    @POST("usuarios/logout")
    suspend fun logout(
        @Body refreshToken: Map<String, String>
    ): Response<Unit>

}

