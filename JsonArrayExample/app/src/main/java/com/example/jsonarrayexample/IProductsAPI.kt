package com.example.jsonarrayexample
///https://fakestoreapi.com/products?limit=1  <- original link
import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IProductsAPI {
 @GET("/products")
 fun getListProducts(
  // below is the name of the variable from the link
  @Query("limit") limit:Int
 ): retrofit2.Call<List<ProductModel>>

}