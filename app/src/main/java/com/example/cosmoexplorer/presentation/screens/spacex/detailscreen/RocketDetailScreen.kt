package com.example.cosmoexplorer.presentation.screens.spacex.detailscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.example.cosmoexplorer.presentation.theme.SuccessGreen
import com.example.cosmoexplorer.presentation.viewmodel.RocketDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketDetail(
    rocketId: String,
    onBackClick: () -> Unit,
    viewModel: RocketDetailViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    fun formatDate(date: String): String {
        return date.split("-").reversed().joinToString("/")
    }

    fun formatUsd(value: Long): String {
        return "US$ ${"%,d".format(value)}"
    }


    LaunchedEffect(rocketId) {
        viewModel.loadRocketDetail(rocketId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            state.errorMessage != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
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
                        .padding(innerPadding)
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

                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                            Text(
                                text = "Technical Details",
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text("First Flight: ${formatDate(rocket.first_flight)}")
                            Text("Success Rate: ${rocket.success_rate_pct}%")
                            Text("Cost per Launch: ${formatUsd(rocket.cost_per_launch)}")
                        }
                    }

                    item {
                        Text(
                            text = "See more on Wikipedia",
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable {
                            }
                        )
                    }
                }
            }
        }
    }
}