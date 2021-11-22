package com.example.singleproductexample

import retrofit2.Call
import retrofit2.http.GET

interface ProductAPI {
    @GET("/products/1")
    fun getProductsInfo(): Call<productModel>



}