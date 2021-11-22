package com.example.helloworldexampleweek7api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val helloTextView:TextView = findViewById(R.id.text_view)

        val retrofitService = Retrofit.Builder().baseUrl("https://publicobject.com")
                ///line below convert the response to string
            .addConverterFactory(ScalarsConverterFactory.create()).build()
            // the lineS below is to get the API
            val retrofitAPI = retrofitService.create(RetrofitAPI::class.java)
            // The function below is to hold the response
        retrofitAPI.getHelloWorldText().enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                // the code() is the status code
                when(response.code()){
                    200 ->{
                        Log.d("MainActivity",response.body()!!)
                        helloTextView.text = response.body()!!
                    }
                }
            }
            // The function below is to check if the request code failed or timeout
            override fun onFailure(call: Call<String>, t: Throwable) {
            }


        })

    }
}