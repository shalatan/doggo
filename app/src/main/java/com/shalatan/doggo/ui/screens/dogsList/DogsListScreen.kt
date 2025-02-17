package com.shalatan.doggo.ui.screens.dogsList

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shalatan.doggo.data.local.DogEntity
import com.shalatan.doggo.ui.screens.CustomButton

@Composable
fun DogsListScreen(modifier: Modifier = Modifier, viewModel: DogsListViewModel) {

    viewModel.processIntents(DogsListIntents.FetchAllDogs)
    val state by viewModel.state.collectAsState()
    Log.e("Custom DogsListScreen: ", "currentState: $state")

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state) {
            is DogsListState.AllDogImages -> {
                val images = (state as DogsListState.AllDogImages).images
                LazyRow(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(images) { dogEntity ->
                        DogsListItem(modifier = Modifier.size(240.dp), dogEntity = dogEntity)
                    }
                }
            }

            DogsListState.Idle -> {
                Text("No dog images found")
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        CustomButton(text = "Clear Dogs!") {
            viewModel.processIntents(DogsListIntents.ClearData)
        }
    }
}

@Composable
fun DogsListItem(modifier: Modifier = Modifier, dogEntity: DogEntity) {
    AsyncImage(
        model = dogEntity.url,
        contentDescription = "dog image",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}