package com.example.doggyapp.model


import com.google.gson.annotations.SerializedName

data class DoggyModel(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)