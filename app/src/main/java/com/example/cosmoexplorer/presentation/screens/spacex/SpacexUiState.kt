package com.example.cosmoexplorer.presentation.screens.spacex

import com.example.cosmoexplorer.data.model.Rocket

data class SpacexUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val name: String? = null
)