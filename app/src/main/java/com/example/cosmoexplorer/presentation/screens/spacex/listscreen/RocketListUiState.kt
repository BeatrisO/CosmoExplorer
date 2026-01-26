package com.example.cosmoexplorer.presentation.screens.spacex.listscreen

data class RocketListUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val name: String? = null,
    val description: String? = null,
    val imageurl: String? = null
)