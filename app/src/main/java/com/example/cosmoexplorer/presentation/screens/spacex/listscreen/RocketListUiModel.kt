package com.example.cosmoexplorer.presentation.screens.spacex.listscreen

data class RocketListUiModel(
    val id: String,
    val name: String,
    val isActive: Boolean,
    val statusText: String,
    val imageUrl: String?
)