package com.example.lapjson_week7_tue

import retrofit2.Call
import retrofit2.http.GET

interface CoffeeAPI {

    @GET("/api/coffee/random_coffee")
    fun getProductsInfo(): Call<CoffeModel>

}