package com.example.jsonarrayexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val product = mutableListOf<ProductModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productRecyclerView:RecyclerView = findViewById(R.id.product_recyclerview)
        val productsAapter = ProductRecyclerViewAdapter(product)
        productRecyclerView.adapter = productsAapter

        val retrofit = Retrofit.Builder().baseUrl("https://fakestoreapi.com").addConverterFactory(GsonConverterFactory.create()).build()
        val productAPI = retrofit.create(IProductsAPI::class.java)
        productAPI.getListProducts(3).enqueue(object :Callback<List<ProductModel>>{
            override fun onResponse(
                call: Call<List<ProductModel>>,
                response: Response<List<ProductModel>>
            ) {
               response.body()?.run {
                   product.addAll(this)
                   productsAapter.notifyDataSetChanged()
               }
            }

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }
}