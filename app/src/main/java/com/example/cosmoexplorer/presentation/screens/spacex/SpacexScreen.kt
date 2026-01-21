package com.example.cosmoexplorer.presentation.screens.spacex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cosmoexplorer.presentation.viewmodel.SpaceXViewModel

@Composable
fun SpacexScreen(
    viewModel: SpaceXViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        state.name?.let {
            Text(text = it,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        state.description?.let {
            Text(text = it,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
