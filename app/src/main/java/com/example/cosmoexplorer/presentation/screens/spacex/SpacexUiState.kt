package com.example.cosmoexplorer.presentation.screens.spacex


data class SpacexUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val name: String? = null,
    val description: String? = null
)