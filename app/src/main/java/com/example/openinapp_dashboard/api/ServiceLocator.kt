package com.example.openinapp_dashboard.api

import com.example.openinapp_dashboard.repository.ChartRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator {
    private const val BASE_URL = "https://api.inopenapp.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    val repository: ChartRepository by lazy {
        ChartRepository(apiService)
    }
}