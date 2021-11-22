package com.example.bore_app

import android.telecom.Call
import retrofit2.http.GET
///https://www.boredapi.com/api/activity

interface ActivityAPI {
  @GET("/api/activity")
  fun getActivity(): retrofit2.Call<ToActivityModel>
}