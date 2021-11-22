package com.example.universities_app

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface uniAPI {
    /// original link: http://universities.hipolabs.com/search?country=saudi%20arabia

    @GET("/search")
    fun getUniversities(
        @Query("country") country:String

    ): Call<List<uniModel>>

}