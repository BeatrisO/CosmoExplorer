package com.example.cosmoexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cosmoexplorer.data.model.Rocket
import com.example.cosmoexplorer.presentation.screens.spacex.detailscreen.RocketDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RocketDetailViewModel : ViewModel() {

    private val state = MutableStateFlow(RocketDetailUiState())
    val uiState: StateFlow<RocketDetailUiState> = state

    fun setRocket(rocket: Rocket) {
        state.value = RocketDetailUiState(rocket = rocket)
    }
}
