package com.shalatan.doggo.ui

sealed class AppIntents {
    object FetchDogImage : AppIntents()
    object ClearData : AppIntents()
}

sealed class AppState {
    object Idle: AppState()
    object GeneratingImage: AppState()
    data class DogImageSuccess(val imageUrl: String): AppState()
    data class Error(val message: String): AppState()
    data class DogImages(val images: List<String>): AppState()
}