package com.example.memes_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val memes = mutableListOf<Meme>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val memeRecyclerView: RecyclerView = findViewById(R.id.memeRecyclerView_layout)
        val memeAapter = MemesAdapter(memes)
        memeRecyclerView.adapter = memeAapter

        val retrofit = Retrofit.Builder().baseUrl("https://api.imgflip.com").addConverterFactory(
            GsonConverterFactory.create()).build()
        val memeAPI = retrofit.create(MemesAPI::class.java)

        memeAPI.getMemes().enqueue(object:Callback<memeModel> {
            override fun onResponse(call: Call<memeModel>, response: Response<memeModel>) {
                response.body()?.run {
                    Log.d("memes","$this")
                    memes.addAll(this.data.memes)
                    memeAapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<memeModel>, t: Throwable) {
            }


        })
    }
}