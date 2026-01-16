package com.example.space.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL_APOD = "https://api.nasa.gov/"

    private const val BASE_URL_SPACEX = "https://api.spacexdata.com/v4/"

    val apiApod: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_APOD)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val apiSpaceX: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_SPACEX)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}