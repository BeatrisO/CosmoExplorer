package com.example.cosmoexplorer.presentation.screens.spacex.detailscreen

data class RocketDetailUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val rocket: RocketDetailUiModel? = null
)