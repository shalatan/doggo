package com.shalatan.doggo.ui.screens.dogsList

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.shalatan.doggo.data.local.DogDatabase
import com.shalatan.doggo.data.local.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DogsListViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "Custom: DogsListViewModel"

    private val repository = DogRepository(DogDatabase.getInstance(application).dogDao())

    private val _state = MutableStateFlow<DogsListState>(DogsListState.Empty)
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
                        _state.value = DogsListState.Empty
                    } else {
                        _state.value = DogsListState.AllDogImages(dogEntities)
                    }
                    Log.d(TAG, "fetchedDogImages: ${dogEntities.size}")
                }
        }
    }

    private fun clearData() {
        viewModelScope.launch {
            _state.value = DogsListState.Empty
            repository.clearDatabase()
        }
    }
}