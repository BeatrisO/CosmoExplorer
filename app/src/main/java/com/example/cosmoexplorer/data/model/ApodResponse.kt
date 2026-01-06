package com.example.space.data.model

data class ApodResponse(
    val title: String,
    val explanation: String,
    val url: String,
    val hdurl: String?,
    val media_type: String,
    val date: String
)