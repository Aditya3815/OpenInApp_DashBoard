package com.example.openinapp_dashboard.api

import com.example.openinapp_dashboard.models.OverallUrlChart
import com.example.openinapp_dashboard.models.TopLink
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("api/v1/dashboardNew")
    suspend fun getDashboardData(@Header("Authorization") token: String): DashboardResponse

    @GET("dashboardNew")
    suspend fun getDashboardChart(@Header("Authorization") token: String): DashboardResponse
}

data class DashboardResponse(
    val top_links: List<TopLink>,
    val status: Boolean,
    val statusCode: Int,
    val message: String,
    val data: DashboardData
)

data class DashboardData(
    val overall_url_chart: OverallUrlChart
)