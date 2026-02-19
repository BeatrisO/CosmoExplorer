package com.example.cosmoexplorer.presentation.screens.spacex.listscreen


data class RocketListUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val rockets: List<RocketListUiModel> = emptyList()
)
