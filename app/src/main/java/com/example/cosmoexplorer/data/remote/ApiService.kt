package com.example.space.data.remote

import com.example.space.data.model.ApodResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("planetary/apod")
    suspend fun getApod(
        @Query("api_key") apiKey: String): ApodResponse
}