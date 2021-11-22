package com.example.memes_app


import com.google.gson.annotations.SerializedName

data class memeModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean
)