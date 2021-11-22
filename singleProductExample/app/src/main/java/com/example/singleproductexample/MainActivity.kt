package com.example.singleproductexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//1- CREATE YOUR MODEL
//2- CREATE API INTERFACE

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titleTextView:TextView = findViewById(R.id.productTitle)
        val descriptionTextView:TextView = findViewById(R.id.product_description)
        val categoryTextView:TextView = findViewById(R.id.product_category)
        val priceTextView:TextView = findViewById(R.id.product_price)

        val retrofit = Retrofit.Builder().baseUrl("https://fakestoreapi.com")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val retrofitAPI = retrofit.create(ProductAPI::class.java)
        retrofitAPI.getProductsInfo().enqueue(object :Callback<productModel>{
            override fun onResponse(call: Call<productModel>, response: Response<productModel>) {
                response.body()?.run {

                    titleTextView.text = title
                    descriptionTextView.text = description
                    categoryTextView.text = category
                    priceTextView.text = "$price SAR"
                }
            }

            override fun onFailure(call: Call<productModel>, t: Throwable) {
            }


        })










    }
}