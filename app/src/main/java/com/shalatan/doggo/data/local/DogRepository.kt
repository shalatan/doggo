package com.shalatan.doggo.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DogRepository(private val dao: DogDao) {
    suspend fun insertDogImage(imageUrl: String) {
        dao.insertDogImage(DogEntity(url = imageUrl))
    }

    fun getAllDogImages(): Flow<List<DogEntity>> = flow {
        emit(dao.getAllDogImages())
    }

    suspend fun clearDatabase() {
        dao.clearData()
    }
}