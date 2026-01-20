package com.example.cosmoexplorer.presentation.viewmodel

import com.example.cosmoexplorer.presentation.screens.spacex.SpacexUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmoexplorer.data.repository.SpaceXRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SpaceXViewModel(
    private val repository: SpaceXRepository = SpaceXRepository()
) : ViewModel() {

    private val state = MutableStateFlow(
        SpacexUiState(isLoading = true)
    )
    val uiState: StateFlow<SpacexUiState> = state

    init {
        loadRockets()
    }

    private fun loadRockets() {
        viewModelScope.launch {
            try {
                val rockets = repository.getRockets()
                val firstRocketName = rockets.firstOrNull()?.name

                state.value = SpacexUiState(
                    isLoading = false,
                    name = firstRocketName
                )

            } catch (e: Exception) {
                state.value = SpacexUiState(
                    isLoading = false,
                    errorMessage = e.message
                )
            }
        }
    }
}
