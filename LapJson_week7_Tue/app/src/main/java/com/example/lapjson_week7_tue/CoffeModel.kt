package com.example.lapjson_week7_tue


import com.google.gson.annotations.SerializedName

data class CoffeModel(
    @SerializedName("blend_name")
    val blendName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("intensifier")
    val intensifier: String,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("variety")
    val variety: String
)