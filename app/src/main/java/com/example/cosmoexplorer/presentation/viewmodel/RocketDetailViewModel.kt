package com.example.cosmoexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmoexplorer.data.repository.SpaceXRepository
import com.example.cosmoexplorer.extensions.toDisplayDate
import com.example.cosmoexplorer.extensions.toUsd
import com.example.cosmoexplorer.presentation.screens.spacex.detailscreen.RocketDetailUiModel
import com.example.cosmoexplorer.presentation.screens.spacex.detailscreen.RocketDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RocketDetailViewModel(
    private val repository: SpaceXRepository = SpaceXRepository()
) : ViewModel() {

    private val state = MutableStateFlow(RocketDetailUiState(isLoading = true))
    val uiState: StateFlow<RocketDetailUiState> = state

    fun loadRocketDetail(rocketId: String) {
        viewModelScope.launch {
            state.value = RocketDetailUiState(isLoading = true)

            try {
                val rocket = repository
                    .getRockets()
                    .firstOrNull { it.id == rocketId }

                if (rocket == null) {
                    state.value = RocketDetailUiState(
                        isLoading = false,
                        errorMessage = "Rocket not found"
                    )
                    return@launch
                }

                val uiModel = RocketDetailUiModel(
                    name = rocket.name,
                    description = rocket.description,
                    statusText = if (rocket.active) "Active" else "Disabled",
                    firstFlight = rocket.first_flight.toDisplayDate(),
                    successRate = "${rocket.success_rate_pct}%",
                    costPerLaunch = rocket.cost_per_launch.toUsd(),
                    wikipedia = rocket.wikipedia,
                    imageUrl = rocket.flickr_images.firstOrNull()
                )

                state.value = RocketDetailUiState(
                    isLoading = false,
                    rocket = uiModel
                )

            } catch (e: Exception) {
                state.value = RocketDetailUiState(
                    isLoading = false,
                    errorMessage = "Error loading rocket"
                )
            }
        }
    }
}