package com.example.helloworldexampleweek7api

import android.telecom.Call
import retrofit2.http.GET
/// THIS IS THE FULL APIhttps://publicobject.com/helloworld.txt
interface RetrofitAPI {
    // BELOW WE ARE PASSING THE PATH OF THE API
@GET("/helloworld.txt")
fun getHelloWorldText(): retrofit2.Call<String>


}