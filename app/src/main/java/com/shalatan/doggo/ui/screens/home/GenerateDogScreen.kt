package com.shalatan.doggo.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shalatan.doggo.ui.screens.CustomButton

@Composable
fun GenerateDogScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val uiState by viewModel.state.collectAsState()
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val contentHeightModifier = Modifier.height(240.dp)
        when (uiState) {
            is AppState.DogImageSuccess -> AsyncImage(
                model = (uiState as AppState.DogImageSuccess).imageUrl,
                contentDescription = "dog image",
                modifier = contentHeightModifier
            )

            is AppState.GeneratingImage -> {
                CircularProgressIndicator(modifier = contentHeightModifier)
            }

            is AppState.Error -> {
                Text(text = "Error fetching image", modifier = contentHeightModifier)
            }

            is AppState.Idle -> {
                Text(text = "Click button to generate image", modifier = contentHeightModifier)
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        CustomButton(
            modifier = Modifier,
            text = "Generate Dog!"
        ) {
            viewModel.processIntents(AppIntents.FetchDogImage)
        }
    }
}