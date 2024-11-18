package com.example.papalote.utils

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "papalote_prefs"
        private const val KEY_ACCESS_TOKEN = "access_token"
    }

    // Guardar el token
    fun saveToken(token: String) {
        prefs.edit().putString(KEY_ACCESS_TOKEN, token).apply()
    }

    // Obtener el token
    fun getToken(): String? {
        return prefs.getString(KEY_ACCESS_TOKEN, null)
    }

    // Eliminar el token (logout o expiración)
    fun clearToken() {
        prefs.edit().remove(KEY_ACCESS_TOKEN).apply()
    }
}