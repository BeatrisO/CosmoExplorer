package com.example.cosmoexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cosmoexplorer.presentation.components.BottomItem
import com.example.cosmoexplorer.presentation.components.BottomNavigationBar
import com.example.cosmoexplorer.presentation.screens.ApodScreen
import com.example.cosmoexplorer.presentation.screens.SettingsScreen
import com.example.space.ui.theme.CosmoExplorerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CosmoExplorerTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = BottomItem.Apod.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(BottomItem.Apod.route) {
                            ApodScreen()
                        }
                        composable(BottomItem.Settings.route) {
                            SettingsScreen()
                        }
                    }
                }
            }
        }
    }
}