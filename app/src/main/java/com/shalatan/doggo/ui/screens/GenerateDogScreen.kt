package com.shalatan.doggo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shalatan.doggo.ui.AppIntents
import com.shalatan.doggo.ui.AppState
import com.shalatan.doggo.ui.HomeViewModel

@Composable
fun GenerateDogScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val uiState by viewModel.state.collectAsState()
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState) {
                is AppState.DogImageSuccess -> AsyncImage(
                    model = (uiState as AppState.DogImageSuccess).imageUrl,
                    contentDescription = "dog image",
                    modifier = Modifier.size(240.dp)
                )

                is AppState.GeneratingImage -> {
                    CircularProgressIndicator()
                }

                is AppState.Error -> {
                    Text("Error fetching image")
                }

                is AppState.Idle -> {
                    Text("Click button to generate image")
                }

                else -> {}
            }
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(text = "Generate") {
                viewModel.processIntents(AppIntents.FetchDogImage)
            }
        }
    }
}