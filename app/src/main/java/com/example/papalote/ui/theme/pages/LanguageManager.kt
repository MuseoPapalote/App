package com.example.papalote.utils

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

object LanguageManager {
    // Estado para manejar el idioma global
    var language by mutableStateOf("es")

    // Función para cambiar el idioma
    fun changeLanguage(lang: String) {
        language = lang
    }
}