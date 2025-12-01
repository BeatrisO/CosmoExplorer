package com.example.cosmoexplorer.presentation.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class BottomItem(val route: String, val icon: ImageVector, val label: String) {
    object Apod : BottomItem("apod", Icons.Default.Home, "Apod")
    object Settings : BottomItem("settings", Icons.Default.Settings, "Config")

}
@Composable
fun NavigationBar(navController: NavHostController) {

    val items = listOf(
        BottomItem.Apod,
        BottomItem.Settings
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

