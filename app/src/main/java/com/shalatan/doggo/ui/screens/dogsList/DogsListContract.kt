package com.shalatan.doggo.ui.screens.dogsList

import com.shalatan.doggo.data.local.DogEntity

sealed class DogsListIntents {
    data object FetchAllDogs : DogsListIntents()
    data object ClearData : DogsListIntents()
}

sealed class DogsListState {
    data object Empty : DogsListState()
    data class AllDogImages(val images: List<DogEntity>) : DogsListState()
}