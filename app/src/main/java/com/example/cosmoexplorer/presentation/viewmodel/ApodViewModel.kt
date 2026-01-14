package com.example.space.ui.screens.apod

import ApodRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmoexplorer.BuildConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ApodViewModel(
    private val repository: ApodRepository = ApodRepository()
) : ViewModel() {

    private val state = MutableStateFlow(ApodUiState(isLoading = true))
    val uiState: StateFlow<ApodUiState> = state

    init {
        loadApod(BuildConfig.NASA_API_KEY)
    }

    private fun loadApod(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getApod(apiKey)

                state.value = ApodUiState(
                    isLoading = false,
                    title = response.title,
                    explanation = response.explanation,
                    imageUrl = response.url ?: "",
                    date = response.date
                )

            } catch (e: Exception) {
                state.value = ApodUiState(
                    isLoading = false,
                    error = e.message ?: "Error loading APOD"
                )
            }
        }
    }
}

