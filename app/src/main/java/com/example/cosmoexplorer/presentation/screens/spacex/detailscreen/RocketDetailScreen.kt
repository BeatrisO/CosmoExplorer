package com.example.cosmoexplorer.presentation.screens.spacex.detailscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.cosmoexplorer.presentation.viewmodel.RocketDetailViewModel

@Composable
fun RocketDetail(
    rocketId: String,
    viewModel: RocketDetailViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(rocketId) {
        viewModel.loadRocketDetail(rocketId)
    }

    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }

        state.errorMessage != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.errorMessage!!,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        state.rocket != null -> {
            val rocket = state.rocket!!

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {
                    AsyncImage(
                        model = rocket.flickr_images.firstOrNull(),
                        contentDescription = rocket.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    Text(
                        text = rocket.name,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

                item {
                    Text(
                        text = rocket.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}