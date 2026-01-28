package com.example.cosmoexplorer.presentation.screens.spacex.listscreen

import com.example.cosmoexplorer.data.model.Rocket

data class RocketListUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val rockets: List<Rocket> = emptyList()
)
