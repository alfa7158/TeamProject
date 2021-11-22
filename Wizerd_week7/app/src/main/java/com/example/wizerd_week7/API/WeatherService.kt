package com.example.wizerd_week7.API
/// this class is similar to the repository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val Base_Url = "https://api.openweathermap.org"
object WeatherService {

    private val retrofitService = Retrofit.Builder().baseUrl(Base_Url).
    addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherApi = retrofitService.create(IWeatherAPI::class.java)

    suspend fun getWeather(city:String) = weatherApi.getWeather(city)
}