package com.example.space.ui.screens.apod

data class ApodUiState(
    var isLoading: Boolean = false,
    val title: String = "",
    val explanation: String = "",
    val imageUrl: String = "",
    val date: String = "",
    val error: String? = null
)