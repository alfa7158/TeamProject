package com.example.e_commerce.model.products


import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("currentPage")
    val currentPage: Int,
    @SerializedName("pageElements")
    val products: List<Product>,
    @SerializedName("totalPages")
    val totalPages: Int
)