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

                state.value = SpacexUiState(
                    isLoading = false,
                    rockets = rockets
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
