package com.shalatan.doggo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shalatan.doggo.ui.HomeViewModel
import com.shalatan.doggo.ui.screens.DogsListScreen
import com.shalatan.doggo.ui.screens.GenerateDogScreen
import com.shalatan.doggo.ui.screens.HomeScreen


@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel = HomeViewModel()

    NavHost(
        navController = navController, startDestination = "home"
    ) {
        composable(route = "home") {
            HomeScreen(
                navigateToGenerate = { navController.navigate("generate") },
                navigateToListView = { navController.navigate("list") }
            )
        }

        composable(route = "generate") {
            GenerateDogScreen(modifier = modifier, viewModel = viewModel)
        }

        composable(route = "list") {
            DogsListScreen()
        }
    }
}