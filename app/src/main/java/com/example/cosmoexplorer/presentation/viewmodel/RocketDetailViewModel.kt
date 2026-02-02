package com.example.cosmoexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmoexplorer.data.repository.SpaceXRepository
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

                if (rocket != null) {
                    state.value = RocketDetailUiState(
                        isLoading = false,
                        errorMessage = null,
                        rocket = rocket
                    )
                } else {
                    state.value = RocketDetailUiState(
                        isLoading = false,
                        errorMessage = "Rocket not found"
                    )
                }

            } catch (e: Exception) {
                state.value = RocketDetailUiState(
                    isLoading = false,
                    errorMessage = "Error loading rocket"
                )
            }
        }
    }
}