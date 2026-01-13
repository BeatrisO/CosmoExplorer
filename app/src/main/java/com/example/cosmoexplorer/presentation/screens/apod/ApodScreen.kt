package com.example.cosmoexplorer.presentation.screens.apod

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.space.ui.screens.apod.ApodViewModel

@Composable
fun ApodScreen(
    viewModel: ApodViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    val errorMessage = state.error

    val cleanExplanation = remember(state.title, state.explanation) {
        state.explanation
            .removePrefix("${state.title}.")
            .trimStart()
    }

    when {
        state.isLoading -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        errorMessage != null -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        else -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .verticalScroll(rememberScrollState())
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = state.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(330.dp)
                )

                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp, bottom = 26.dp)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = state.title,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            lineHeight = 30.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = cleanExplanation,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 17.sp,
                            lineHeight = 26.sp
                        ),
                        textAlign = TextAlign.Justify
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    Text(
                        text = state.date,
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

