package com.shalatan.doggo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shalatan.doggo.MainActivity
import com.shalatan.doggo.data.local.DogDatabase
import com.shalatan.doggo.data.local.DogRepository
import com.shalatan.doggo.ui.screens.home.HomeViewModel
import com.shalatan.doggo.ui.screens.dogsList.DogsListScreen
import com.shalatan.doggo.ui.screens.dogsList.DogsListViewModel
import com.shalatan.doggo.ui.screens.home.GenerateDogScreen
import com.shalatan.doggo.ui.screens.home.HomeScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier, activity: MainActivity) {
    val navController = rememberNavController()

    val database = DogDatabase.getInstance(activity)
    val repository = DogRepository(database.dogDao())
    val viewModel = HomeViewModel(repository)
    val dogsListViewModel = DogsListViewModel(repository)

    NavHost(
        navController = navController, startDestination = "home"
    ) {
        composable(route = "home") {
            HomeScreen(navigateToGenerate = { navController.navigate("generate") },
                navigateToListView = { navController.navigate("list") })
        }

        composable(route = "generate") {
            GenerateDogScreen(modifier = modifier, viewModel = viewModel)
        }

        composable(route = "list") {
            DogsListScreen(modifier = modifier, viewModel = dogsListViewModel)
        }
    }
}