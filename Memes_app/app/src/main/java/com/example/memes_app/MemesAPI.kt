package com.example.memes_app

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
/// https://api.imgflip.com/get_memes
interface MemesAPI {
    @GET("/get_memes")
    fun getMemes():Call<memeModel>
}