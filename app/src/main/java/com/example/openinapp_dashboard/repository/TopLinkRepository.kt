package com.example.openinapp_dashboard.repository

import com.example.openinapp_dashboard.api.ApiService
import com.example.openinapp_dashboard.models.TopLink
import com.example.openinapp_dashboard.utils.ApiConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TopLinkRepository {
    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getTopLinks(): List<TopLink> {
        val token = "Bearer ${ApiConfig.BEARER_TOKEN}"
        return apiService.getDashboardData(token).top_links
    }
}