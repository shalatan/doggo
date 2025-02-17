package com.shalatan.doggo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shalatan.doggo.ui.screens.home.HomeViewModel
import com.shalatan.doggo.ui.screens.dogsList.DogsListScreen
import com.shalatan.doggo.ui.screens.dogsList.DogsListViewModel
import com.shalatan.doggo.ui.screens.home.GenerateDogScreen
import com.shalatan.doggo.ui.screens.home.HomeScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = "home"
    ) {
        composable(route = "home") {
            HomeScreen(navigateToGenerate = { navController.navigate("generate") },
                navigateToListView = { navController.navigate("list") })
        }

        composable(route = "generate") {
            val viewModel: HomeViewModel = viewModel()
            GenerateDogScreen(modifier = modifier, viewModel = viewModel)
        }

        composable(route = "list") {
            val dogsListViewModel: DogsListViewModel = viewModel()
            DogsListScreen(modifier = modifier, viewModel = dogsListViewModel)
        }
    }
}