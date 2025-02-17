package com.shalatan.doggo.ui.screens.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.shalatan.doggo.data.local.DogDatabase
import com.shalatan.doggo.data.local.DogRepository
import com.shalatan.doggo.data.remote.ApiService
import com.shalatan.doggo.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "Custom: HomeViewModel"

    private val repository = DogRepository(DogDatabase.getInstance(application).dogDao())
    private val dogApi = RetrofitClient.instance.create(ApiService::class.java)

    private val _state = MutableStateFlow<AppState>(AppState.Idle)
    var state = _state.asStateFlow()

    fun processIntents(intents: AppIntents) {
        when (intents) {
            AppIntents.FetchDogImage -> fetchDogImage()
        }
    }

    private fun fetchDogImage() {
        viewModelScope.launch {
            _state.value = AppState.GeneratingImage
            try {
                val response = dogApi.getRandomDogImage()
                Log.e(TAG, "apiResponse: $response")
                repository.insertDogImage(imageUrl = response.message)
                _state.value = AppState.DogImageSuccess(response.message)
            } catch (e: Exception) {
                _state.value = AppState.Error("Error fetching image")
            }
        }
    }
}