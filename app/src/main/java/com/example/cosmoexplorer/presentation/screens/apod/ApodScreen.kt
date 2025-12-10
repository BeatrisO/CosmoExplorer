package com.example.cosmoexplorer.presentation.screens.apod

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.cosmoexplorer.BuildConfig
import com.example.space.ui.screens.apod.ApodViewModel

@Composable
fun ApodScreen(viewModel: ApodViewModel = viewModel()) {

    val state = viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.loadApod(BuildConfig.NASA_API_KEY)
    }

    val cleanExplanation = remember(state.title, state.explanation) {
        state.explanation
            ?.removePrefix("${state.title}.")
            ?.trimStart()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCBC4C4))
            .verticalScroll(rememberScrollState())
    ) {

        AsyncImage(
            model = state.imageUrl,
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
                text = state.title ?: "",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    lineHeight = 30.sp
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(14.dp))

            cleanExplanation?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 17.sp,
                        color = Color.Black,
                        lineHeight = 26.sp
                    ),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            state.date?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = Color.DarkGray
                    ),
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

