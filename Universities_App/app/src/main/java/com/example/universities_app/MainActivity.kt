package com.example.universities_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val uni = mutableListOf<uniModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /// original link: http://universities.hipolabs.com/search?country=saudi%20arabia
        val uniRecyclerView: RecyclerView = findViewById(R.id.uni_recyclerViewLayout)
        val uniAapter = UniRecyclerViewAdapter(uni)
        uniRecyclerView.adapter = uniAapter

        val retrofit =
            Retrofit.Builder().baseUrl("http://universities.hipolabs.com").addConverterFactory(
                GsonConverterFactory.create()
            ).build()
        val uniAPI = retrofit.create(uniAPI::class.java)
        val theButton: Button = findViewById(R.id.myButton)
        val country:EditText = findViewById(R.id.uniEditText)
        theButton.setOnClickListener(){
        uniAPI.getUniversities(country.text.toString()).enqueue(object : Callback<List<uniModel>> {

            override fun onResponse(
                call: Call<List<uniModel>>,
                response: Response<List<uniModel>>
            ) {
                response.body()?.run {
                    uni.addAll(this)

                    uniAapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<uniModel>>, t: Throwable) {

            }


        })
    }
    }
}