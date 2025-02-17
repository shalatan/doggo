package com.shalatan.doggo.ui.screens.home

sealed class AppIntents {
    data object FetchDogImage : AppIntents()
    data object ClearData : AppIntents()
}

sealed class AppState {
    data object Idle : AppState()
    data object GeneratingImage : AppState()
    data class DogImageSuccess(val imageUrl: String) : AppState()
    data class Error(val message: String) : AppState()
}