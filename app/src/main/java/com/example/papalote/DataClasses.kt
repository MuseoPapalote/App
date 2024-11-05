package com.example.papalote

import java.util.Date


data class RegisterRequest(
    val email: String,
    val birthDate: Date,
    val username: String,
    val password: String
)

data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val token: String? = null
)