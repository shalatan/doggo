package com.shalatan.doggo.ui.screens.dogsList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shalatan.doggo.data.local.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DogsListViewModel(private val repository: DogRepository) : ViewModel() {

    private val TAG = "Custom: DogsListViewModel"

    private val _state = MutableStateFlow<DogsListState>(DogsListState.Idle)
    var state = _state.asStateFlow()

    fun processIntents(intents: DogsListIntents) {
        when (intents) {
            DogsListIntents.ClearData -> clearData()
            DogsListIntents.FetchAllDogs -> fetchAllImages()
        }
    }

    private fun fetchAllImages() {
        viewModelScope.launch {
            repository.getAllDogImages()
                .collect { dogEntities ->
                    if (dogEntities.isEmpty()) {
                        _state.value = DogsListState.Idle
                    } else {
                        _state.value = DogsListState.AllDogImages(dogEntities)
                    }
                    Log.d(TAG, "fetchingDogImaged: ${dogEntities.size}")
                }
        }
    }

    private fun clearData() {
        viewModelScope.launch {
            _state.value = DogsListState.Idle
            repository.clearDatabase()
        }
    }
}