package com.example.cosmoexplorer.presentation.screens.spacex.detailscreen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.cosmoexplorer.presentation.viewmodel.RocketDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketDetail(
    rocketId: String,
    onBackClick: () -> Unit,
    viewModel: RocketDetailViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

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
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { innerPadding ->

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.errorMessage != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(state.errorMessage!!)
                }
            }

            state.rocket != null -> {
                val rocket = state.rocket!!

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    AsyncImage(
                        model = rocket.imageUrl,
                        contentDescription = rocket.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                            .align(Alignment.TopCenter),
                        contentScale = ContentScale.Crop
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = innerPadding.calculateBottomPadding()
                            ),
                        contentPadding = PaddingValues(
                            top = 280.dp,
                            bottom = 16.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

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
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                Text(
                                    text = "Technical Details",
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text("First Flight: ${rocket.firstFlight}")
                                Text("Success Rate: ${rocket.successRate}")
                                Text("Cost per Launch: ${rocket.costPerLaunch}")
                            }
                        }

                        item {
                            val context = LocalContext.current

                            Text(
                                text = "See more on Wikipedia",
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    textDecoration = TextDecoration.Underline
                                ),
                                modifier = Modifier.clickable {
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(rocket.wikipedia)
                                    )
                                    context.startActivity(intent)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}