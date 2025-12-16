package com.example.space.ui.screens.apod

import ApodRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ApodViewModel(
    private val repository: ApodRepository = ApodRepository()
) : ViewModel() {

    private var state = MutableStateFlow(ApodUiState())
    var uiState = state.asStateFlow()

    fun loadApod(apiKey: String) {
        state.value.isLoading = true

        viewModelScope.launch {
            try {
                val response = repository.getApod(apiKey)

                state.value = ApodUiState(
                    isLoading = false,
                    title = response.title,
                    explanation = response.explanation,
                    imageUrl = response.url
                )

            } catch (e: Exception) {
                state.value = ApodUiState(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}

