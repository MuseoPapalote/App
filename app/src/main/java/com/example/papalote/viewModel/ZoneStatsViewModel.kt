package com.example.papalote.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papalote.api.Repository
import com.example.papalote.states.ZoneStatsState
import com.example.papalote.zoneRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ZoneStatsViewModel(private val repository: Repository): ViewModel() {
    private val _zoneStatsState = MutableStateFlow<ZoneStatsState>(ZoneStatsState.Idle)
    val zoneState: StateFlow<ZoneStatsState> = _zoneStatsState

    fun fetchZoneStats(nombre_zona: String){
        if (_zoneStatsState.value is ZoneStatsState.Success) return

        viewModelScope.launch{
            _zoneStatsState.value = ZoneStatsState.Loading

            val request = zoneRequest(
                nombre_zona = nombre_zona
            )

            repository.getZoneStats(request).fold(
                onSuccess = { zoneStats ->
                    println("Zone stats received: $zoneStats")
                    _zoneStatsState.value = ZoneStatsState.Success(zoneStats)
                },
                onFailure = { error ->
                    println("Error getting zone stats: ${error.message}")
                    _zoneStatsState.value = ZoneStatsState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }
}