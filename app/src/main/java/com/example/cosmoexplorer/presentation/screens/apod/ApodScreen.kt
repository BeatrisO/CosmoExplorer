package com.example.cosmoexplorer.presentation.screens.apod

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.space.ui.screens.apod.ApodViewModel

@Composable
fun ApodScreen(viewModel: ApodViewModel = viewModel()) {

    val state = viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.loadApod("DEMO_KEY")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCBC4C4))
    ) {

        when {
            state.isLoading -> {
                Text(
                    text = "Carregando...",
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.error != null -> {
                Text(
                    text = "Erro: ${state.error}",
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                ) {

                    AsyncImage(
                        model = state.imageUrl,
                        contentDescription = state.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )

                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = state.title ?: "",
                            color = Color.Black,
                            fontSize = 22.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        state.explanation?.let {
                            Text(
                                text = it,
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        state.date?.let {
                            Text(
                                text = it,
                                color = Color.DarkGray,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

