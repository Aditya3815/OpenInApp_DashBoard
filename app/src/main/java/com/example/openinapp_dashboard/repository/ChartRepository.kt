package com.example.openinapp_dashboard.repository

import com.example.openinapp_dashboard.api.ApiService
import com.example.openinapp_dashboard.api.DashboardData

class ChartRepository(private val apiService: ApiService) {
    suspend fun getDashboardData(token: String): Result<DashboardData> {
        return try {
            val response = apiService.getDashboardChart(token)
            if (response.status) {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}