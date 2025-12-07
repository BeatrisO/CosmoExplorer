package com.example.cosmoexplorer.presentation.screens.apod

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cosmoexplorer.BuildConfig
import com.example.space.ui.screens.apod.ApodViewModel

@Composable
fun ApodScreen(viewModel: ApodViewModel = viewModel()) {

    val state = viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.loadApod(BuildConfig.NASA_API_KEY)
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
                Text(
                    text = state.title ?: "",
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .verticalScroll(rememberScrollState()),
//                ) {
//
//                    AsyncImage(
//                        model = state.imageUrl,
//                        contentDescription = state.title,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(300.dp)
//                    )
//
//                    Column(
//                        modifier = Modifier
//                            .padding(16.dp)
//                            .fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//
//                        Text(
//                            text = state.title ?: "",
//                            color = Color.Black,
//                            fontSize = 22.sp,
//                            fontWeight = FontWeight.Bold,
//                            textAlign = TextAlign.Center
//                        )
//
//                        Spacer(modifier = Modifier.height(12.dp))
//
//                        state.explanation?.let {
//                            Text(
//                                text = it,
//                                color = Color.Black,
//                                fontSize = 16.sp,
//                                textAlign = TextAlign.Justify,
//                                lineHeight = 22.sp,
//                                modifier = Modifier.fillMaxWidth()
//                            )
//                        }
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        state.date?.let {
//                            Text(
//                                text = it,
//                                color = Color.DarkGray,
//                                fontSize = 14.sp,
//                                textAlign = TextAlign.Center
//                            )
//                        }
//                    }
//                }
            }
        }
    }
}

