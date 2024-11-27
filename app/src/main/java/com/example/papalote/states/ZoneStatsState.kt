package com.example.papalote.states

import com.example.papalote.zoneResponse

sealed class ZoneStatsState {
    object Idle : ZoneStatsState()
    object Loading : ZoneStatsState()
    data class Success(val zoneStats: zoneResponse) : ZoneStatsState()
    data class Error(val message: String) : ZoneStatsState()
}