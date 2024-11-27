package com.example.papalote.api
import com.example.papalote.LoginRequest
import com.example.papalote.LoginResponse
import com.example.papalote.RegisterRequest
import com.example.papalote.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.papalote.UserResponse
import com.example.papalote.VisitRequest
import com.example.papalote.VisitResponse
import com.example.papalote.utils.TokenManager
import com.example.papalote.zoneRequest
import com.example.papalote.zoneResponse

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
                val token = tokenManager.getAccessToken()
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

    suspend fun registerVisit(request: VisitRequest): Result<VisitResponse>{
        return withContext(Dispatchers.IO){
            try{
                val token = tokenManager.getAccessToken()
                if(token == null){
                    println("Token not found")
                    Result.failure(Exception("Token not found"))
                }else{
                    println("Using token: Bearer $token")
                    val response = apiService.registerVisit("Bearer ${token}",request)
                    println("Response: $response")
                    if (response.isSuccessful){
                        println("Visit registered successfully")
                        Result.success(response.body()!!)
                    }else{
                        println("No se pudo xd ${response.message()}")
                        Result.failure(Exception("Failed to register visit: ${response.message()}"))
                    }
                }
            } catch(e: Exception){
                Result.failure(e)
            }
        }
    }

    suspend fun getZoneStats(request: zoneRequest): Result<zoneResponse>{
        return withContext(Dispatchers.IO){
            try{
                val token = tokenManager.getAccessToken()
                if(token == null){
                    println("Token not found")
                    Result.failure(Exception("Token not found"))
                }else{
                    println("Using token: Bearer $token")
                    val response = apiService.getZoneStats("Bearer ${token}",request)
                    println("Response: $response")
                    if (response.isSuccessful){
                        println("Zone stats fetched successfully")
                        Result.success(response.body()!!)
                    }else{
                        println("No se pudo xd ${response.message()}")
                        Result.failure(Exception("Failed to fetch zone stats: ${response.message()}"))
                    }
                }
            } catch(e: Exception){
                Result.failure(e)
            }
        }
    }

    suspend fun verifyRefreshToken(): Boolean {
        val refreshToken = tokenManager.getRefreshToken() ?: return false
        return try{
            val response = apiService.verifyRefreshToken(mapOf("refreshToken" to refreshToken))
            response.isSuccessful
        }catch(e: Exception){
            e.printStackTrace()
            false
        }
    }

    suspend fun refreshAccessToken(): String? {
        val refreshToken = tokenManager.getRefreshToken() ?: return null
        return try{
            val response = apiService.refreshAccessToken(mapOf("refreshToken" to refreshToken))
            if(response.isSuccessful){
                val newAccessToken = response.body()?.accessToken
                if(!newAccessToken.isNullOrEmpty()){
                    tokenManager.saveAccessToken(newAccessToken)
                }
                newAccessToken
            }else{
                null
            }
        } catch(e: Exception){
            e.printStackTrace()
            null
        }
    }

    suspend fun logout(){
        apiService.logout(mapOf("refreshToken" to tokenManager.getRefreshToken()!!))
        tokenManager.clearAccessToken()
        tokenManager.clearRefreshToken()
    }

    // Método público para guardar el token
    fun saveAccessToken(token: String) {
        tokenManager.saveAccessToken(token)
    }

    fun saveRefreshToken(token: String) {
        tokenManager.saveRefreshToken(token)
    }

    // Método público para obtener el token
    fun getAccessToken(): String? {
        return tokenManager.getAccessToken()
    }

    fun getRefreshToken(): String? {
        return tokenManager.getRefreshToken()
    }

    // Método público para eliminar el token
    fun clearAccessToken() {
        tokenManager.clearAccessToken()
    }

    fun clearRefreshToken() {
        tokenManager.clearRefreshToken()
    }
}
