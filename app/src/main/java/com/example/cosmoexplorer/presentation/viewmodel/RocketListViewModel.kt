package com.example.cosmoexplorer.presentation.viewmodel

import com.example.cosmoexplorer.presentation.screens.spacex.listscreen.RocketListUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmoexplorer.data.repository.SpaceXRepository
import com.example.cosmoexplorer.presentation.screens.spacex.listscreen.RocketListUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RocketListViewModel(
    private val repository: SpaceXRepository = SpaceXRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(RocketListUiState(isLoading = true))
    val uiState: StateFlow<RocketListUiState> = _uiState.asStateFlow()

    init {
        loadRockets()
    }

    private fun loadRockets() {
        viewModelScope.launch {

            _uiState.value = RocketListUiState(isLoading = true)

            try {
                val rockets = repository.getRockets()

                val uiModels = rockets.map { rocket ->
                    RocketListUiModel(
                        id = rocket.id,
                        name = rocket.name,
                        isActive = rocket.active,
                        statusText = if (rocket.active) "Active" else "Disabled",
                        imageUrl = rocket.flickr_images.firstOrNull()
                    )
                }

                _uiState.value = RocketListUiState(
                    isLoading = false,
                    rockets = uiModels
                )
            } catch (e: Exception) {

                _uiState.value = RocketListUiState(
                    isLoading = false,
                    errorMessage = "Error loading rockets"
                )
            }
        }
    }
}