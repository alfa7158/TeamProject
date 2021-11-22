package com.example.doggyapp.api

import com.example.doggyapp.model.DoggyModel
import retrofit2.Response
import retrofit2.http.GET

interface DoggyAPI {
    //https://dog.ceo/api/breeds/image/random
    @GET("/api/breeds/image/random")
    suspend fun getDogsPictures(): Response<DoggyModel>

}