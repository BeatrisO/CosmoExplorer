package com.example.cosmoexplorer.presentation.screens.spacex.detailscreen

data class RocketDetailUiModel(
    val name: String,
    val description: String,
    val statusText: String,
    val firstFlight: String,
    val successRate: String,
    val costPerLaunch: String,
    val wikipedia: String,
    val imageUrl: String?
)