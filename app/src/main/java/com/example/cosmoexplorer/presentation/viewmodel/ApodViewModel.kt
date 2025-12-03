package com.example.space.ui.screens.apod

import ApodRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ApodViewModel(
    private val repository: ApodRepository = ApodRepository()
) : ViewModel() {

    var uiState = ApodUiState()
        private set

    fun loadApod(apiKey: String) {
        uiState = uiState.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val response = repository.getApod(apiKey)

                uiState = uiState.copy(
                    isLoading = false,
                    title = response.title,
                    explanation = response.explanation,
                    imageUrl = response.url,
                    error = null
                )

            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}

