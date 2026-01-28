package com.example.cosmoexplorer.presentation.screens.spacex.detailscreen

import com.example.cosmoexplorer.data.model.Rocket

data class RocketDetailUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val rocket: Rocket? = null
)
