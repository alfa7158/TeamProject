package com.example.facebookapp_warm_up


import com.google.gson.annotations.SerializedName

data class PostsModel(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)