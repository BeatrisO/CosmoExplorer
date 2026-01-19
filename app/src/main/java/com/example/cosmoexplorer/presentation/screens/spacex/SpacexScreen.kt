package com.example.cosmoexplorer.presentation.screens.spacex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cosmoexplorer.presentation.viewmodel.SpaceXViewModel

@Composable
fun SpacexScreen(
    viewModel: SpaceXViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = state.errorMessage
                ?: state.rockets.firstOrNull()?.name.orEmpty(),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
