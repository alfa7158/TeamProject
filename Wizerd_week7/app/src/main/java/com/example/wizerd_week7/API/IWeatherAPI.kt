package com.example.wizerd_week7.API

import com.example.wizerd_week7.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// original link: https://api.openweathermap.org/data/2.5/weather?q=khobar&appid=c32e7a8ef14ee53eeacf6bf7c89ab872&units=metric
interface IWeatherAPI {

    @GET("/data/2.5/weather?appid=0714414e3a9a082a2d80bf056a58e457&units=metric")
    suspend fun getWeather(
        @Query("q") city:String
    ) : Response<WeatherModel>

}