package com.example.lapjson_week7_tue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val blendTextView: TextView = findViewById(R.id.coffeeBlend)
        val originTextView: TextView = findViewById(R.id.coffee_origin)
        val noteTextView: TextView = findViewById(R.id.coffeNote)

        val retrofit = Retrofit.Builder().baseUrl("https://random-data-api.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val retrofitAPI = retrofit.create(CoffeeAPI::class.java)
        retrofitAPI.getProductsInfo().enqueue(object : Callback<CoffeModel> {
            override fun onResponse(call: Call<CoffeModel>, response: Response<CoffeModel>) {
                response.body()?.run {

                    blendTextView.text = blendName
                    originTextView.text = origin
                    noteTextView.text = notes


                }
            }

            override fun onFailure(call: Call<CoffeModel>, t: Throwable) {
            }


        })
    }
}