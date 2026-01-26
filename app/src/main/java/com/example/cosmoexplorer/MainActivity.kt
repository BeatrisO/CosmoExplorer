package com.example.cosmoexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cosmoexplorer.presentation.components.BottomItem
import com.example.cosmoexplorer.presentation.components.BottomNavigationBar
import com.example.cosmoexplorer.presentation.screens.apod.ApodScreen
import com.example.cosmoexplorer.presentation.screens.settings.SettingsScreen
import com.example.cosmoexplorer.presentation.screens.spacex.listscreen.SpacexScreen
import com.example.cosmoexplorer.presentation.theme.CosmoExplorerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            CosmoExplorerTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = BottomItem.Apod.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(BottomItem.Apod.route) { ApodScreen() }
                        composable(BottomItem.Spacex.route) { SpacexScreen() }
                        composable(BottomItem.Settings.route) { SettingsScreen() }
                    }
                }
            }
        }
    }
}
