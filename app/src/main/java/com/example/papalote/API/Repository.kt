package com.example.papalote.api
import com.example.papalote.LoginRequest
import com.example.papalote.LoginResponse
import com.example.papalote.RegisterRequest
import com.example.papalote.RegisterResponse
import com.example.papalote.TriviaAnswerRequest
import com.example.papalote.TriviaAnswerResponse
import com.example.papalote.TriviaQuestion
import com.example.papalote.TriviaQuestionsByZoneRequest
import com.example.papalote.TriviaAnswersResponse
import com.example.papalote.TriviaAnswersListType
import com.example.papalote.EncuestaRequest
import com.example.papalote.EncuestaResponse
import com.example.papalote.RetrofitClient.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.papalote.UserResponse
import com.example.papalote.utils.TokenManager

class Repository(private val apiService: ApiService, private val tokenManager: TokenManager) {

    suspend fun registerUser(request: RegisterRequest): Result<RegisterResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.registerUser(request)
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Registration failed: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun loginUser(request: LoginRequest): Result<LoginResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.loginUser(request)
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Login failed: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getUserInfo(): Result<UserResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val token = tokenManager.getToken()
                if (token == null) {
                    println("Token not found")
                    Result.failure(Exception("Token not found"))
                } else {
                    println("Using token: Bearer $token") // Registro para depuración
                    val response = apiService.getUserInfo("Bearer $token")
                    if (response.isSuccessful) {
                        Result.success(response.body()!!)
                    } else {
                        Result.failure(Exception("Failed to fetch user info: ${response.message()}"))
                    }
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun obtenerPreguntasPorZona(zona: String): Result<List<TriviaQuestion>> {
        return withContext(Dispatchers.IO) {
            try {
                val token = tokenManager.getToken()
                if (token.isNullOrEmpty()) {
                    Result.failure(Exception("Token no encontrado. El usuario no está autenticado."))
                } else {
                    val request = TriviaQuestionsByZoneRequest(nombre_zona = zona)
                    val response = apiService.obtenerPreguntasPorZona("Bearer $token", request)
                    if (response.isSuccessful) {
                        Result.success(response.body() ?: emptyList()) // Devuelve una lista vacía si el cuerpo es nulo
                    } else {
                        Result.failure(Exception("Error al obtener preguntas: ${response.message()}"))
                    }
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }


    suspend fun enviarRespuestaTrivia(request: TriviaAnswerRequest): Result<TriviaAnswerResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val accessToken = "Bearer ${tokenManager.getToken()}"
                println("AccessToken enviado al backend: $accessToken") // Verifica el token enviado

                val response = apiService.enviarTriviaAnswer(accessToken, request)
                if (response.isSuccessful) {
                    val respuesta = response.body()
                    if (respuesta != null) {
                        println("Respuesta del backend recibida: $respuesta") // Verifica el contenido de la respuesta
                        println("ID de usuario en la respuesta: ${respuesta.id_usuario}") // Verifica el ID del usuario
                        Result.success(respuesta)
                    } else {
                        println("Error: Respuesta del backend es nula")
                        Result.failure(Exception("Respuesta nula del backend"))
                    }
                } else {
                    println("Error recibido del backend: ${response.message()}") // Captura errores del backend
                    Result.failure(Exception("Error al enviar respuesta: ${response.message()}"))
                }
            } catch (e: Exception) {
                println("Excepción al realizar la solicitud: ${e.message}")
                Result.failure(e)
            }
        }
    }

    suspend fun crearEncuesta(request: EncuestaRequest): Result<EncuestaResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val token = tokenManager.getToken()
                if (token.isNullOrEmpty()) {
                    Result.failure(Exception("Token no encontrado. El usuario no está autenticado."))
                } else {
                    val response = apiService.crearEncuesta("Bearer $token", request)
                    if (response.isSuccessful) {
                        val respuesta = response.body()
                        if (respuesta != null) {
                            println("Respuesta del backend recibida: $respuesta")
                            Result.success(respuesta)
                        } else {
                            println("Error: Respuesta del backend es nula")
                            Result.failure(Exception("Respuesta nula del backend"))
                        }
                    } else {
                        println("Error recibido del backend: ${response.message()}")
                        Result.failure(Exception("Error al crear la encuesta: ${response.message()}"))
                    }
                }
            } catch (e: Exception) {
                println("Excepción al realizar la solicitud: ${e.message}")
                Result.failure(e)
            }
        }
    }



    suspend fun obtenerTriviaAnswers(): List<TriviaAnswersListType> {
        return apiService.obtenerTriviaAnswers().respuestas
    }



    // Método público para guardar el token
    fun saveToken(token: String) {
        tokenManager.saveToken(token)
    }

    // Método público para obtener el token
    fun getToken(): String? {
        return tokenManager.getToken()
    }
}
