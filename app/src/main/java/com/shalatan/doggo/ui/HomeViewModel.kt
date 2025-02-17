package com.shalatan.doggo.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shalatan.doggo.data.local.DogRepository
import com.shalatan.doggo.data.remote.ApiService
import com.shalatan.doggo.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: DogRepository) : ViewModel() {

    private val TAG = "Custom: HomeViewModel"

    private val _state = MutableStateFlow<AppState>(AppState.Idle)
    var state = _state.asStateFlow()

    private val dogApi = RetrofitClient.instance.create(ApiService::class.java)

    fun processIntents(intents: AppIntents) {
        when (intents) {
            AppIntents.FetchDogImage -> fetchDogImage()
            AppIntents.ClearData -> clearData()
        }
    }

    private fun fetchDogImage() {
        viewModelScope.launch {
            _state.value = AppState.GeneratingImage
            try {
                val response = dogApi.getRandomDogImage()
                Log.e(TAG, "response: $response")
                repository.insertDogImage(imageUrl = response.message)
                _state.value = AppState.DogImageSuccess(response.message)
            } catch (e: Exception) {
                _state.value = AppState.Error("Error fetching image")
            }
        }
    }

    private fun clearData() {

    }
}