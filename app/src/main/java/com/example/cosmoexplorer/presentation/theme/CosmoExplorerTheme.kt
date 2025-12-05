package com.example.cosmoexplorer.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color.Black,
    onPrimary = Color.White,
    background = LightGrayBackground,
    onBackground = Color.Black,
    surface = LightGrayBackground,
    onSurface = Color.Black
)

@Composable
fun CosmoExplorerTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}