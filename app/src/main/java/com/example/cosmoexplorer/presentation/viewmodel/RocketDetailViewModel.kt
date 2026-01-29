package com.example.cosmoexplorer.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmoexplorer.data.repository.SpaceXRepository
import com.example.cosmoexplorer.presentation.screens.spacex.detailscreen.RocketDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RocketDetailViewModel(
    private val repository: SpaceXRepository = SpaceXRepository(),
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val state = MutableStateFlow(RocketDetailUiState(isLoading = true))
    val uiState: StateFlow<RocketDetailUiState> = state

    private val rocketName: String =
        savedStateHandle["rocketName"] ?: ""

    init {
        loadRocket()
    }

    private fun loadRocket() {
        viewModelScope.launch {
            state.value = RocketDetailUiState(isLoading = true)

            try {
                val rocket = repository
                    .getRockets()
                    .first { it.name == rocketName }

                state.value = RocketDetailUiState(
                    isLoading = false,
                    rocket = rocket
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
