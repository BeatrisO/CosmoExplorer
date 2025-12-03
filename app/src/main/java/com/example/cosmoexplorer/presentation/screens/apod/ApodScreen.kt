package com.example.cosmoexplorer.presentation.screens.apod

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.space.ui.screens.apod.ApodViewModel

@Composable
fun ApodScreen(viewModel: ApodViewModel = viewModel()) {

    val state = viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.loadApod("DEMO_KEY")
    }

    when {
        state.isLoading -> Text("Carregando...")

        state.error != null -> Text("Erro: ${state.error}")

        else -> Column {
            Text(state.title ?: "")
            Text(state.explanation ?: "")
            AsyncImage(
                model = state.imageUrl,
                contentDescription = null
            )
        }
    }
}

