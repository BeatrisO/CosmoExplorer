package com.example.cosmoexplorer.presentation.viewmodel

import com.example.cosmoexplorer.presentation.screens.spacex.listscreen.RocketListUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmoexplorer.data.repository.SpaceXRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RocketListViewModel(
    private val repository: SpaceXRepository = SpaceXRepository()
) : ViewModel() {

    private val state = MutableStateFlow(RocketListUiState(isLoading = true))
    val uiState: StateFlow<RocketListUiState> = state

    init {
        loadRockets()
    }

    private fun loadRockets() {
        viewModelScope.launch {
            state.value = RocketListUiState(isLoading = true)

            try {
                val rockets = repository.getRockets()

                state.value = RocketListUiState(
                    isLoading = false,
                    rockets = rockets
                )
            } catch (e: Exception) {
                state.value = RocketListUiState(
                    isLoading = false,
                    errorMessage = "Error loading rockets"
                )
            }
        }
    }
}