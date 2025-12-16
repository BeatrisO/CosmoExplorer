package com.example.space.ui.screens.apod

data class ApodUiState(
    var isLoading: Boolean = false,
    val title: String? = null,
    val explanation: String? = null,
    val imageUrl: String? = null,
    val date: String? = null,
    val error: String? = null
)