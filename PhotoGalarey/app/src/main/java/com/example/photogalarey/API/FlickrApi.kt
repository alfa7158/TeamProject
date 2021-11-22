package com.example.photogalarey.API

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface FlickrApi {

    @GET("services/rest/?method=flickr.interestingness.getList" +
            "&api_key=REPLACE_ME_WITH_A_REAL_KEY" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s")
    fun fetchPhotos(): Call<FlickerResponse>

    @GET
    fun fetchUrlBytes(@Url url: String): Call<ResponseBody>
}