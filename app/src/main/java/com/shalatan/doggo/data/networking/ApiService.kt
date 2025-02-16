package com.shalatan.doggo.data.networking

import retrofit2.http.GET

interface ApiService {

    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): DogImageResponse
}