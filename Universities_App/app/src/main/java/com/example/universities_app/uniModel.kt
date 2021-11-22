package com.example.universities_app


import com.google.gson.annotations.SerializedName

data class uniModel(
    @SerializedName("alpha_two_code")
    val alphaTwoCode: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("domains")
    val domains: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("state-province")
    val stateProvince: String,
    @SerializedName("web_pages")
    val webPages: List<String>
)