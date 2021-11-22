package com.example.doggyapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.example.doggyapp.api.DoggyAPI
import com.example.doggyapp.model.DoggyModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val theDogImageView:ImageView = findViewById(R.id.dog_imageView)
        val theDogButton:Button = findViewById(R.id.dog_button)

        val retrofit = Retrofit.Builder().baseUrl("https://dog.ceo").addConverterFactory(
            GsonConverterFactory.create()).build()
        val dogApi = retrofit.create(DoggyAPI::class.java)


           theDogButton.setOnClickListener(){
               GlobalScope.launch(Dispatchers.IO){

                withContext(Dispatchers.Main){
                    val theDogs = dogApi.getDogsPictures()

                    Log.d(theDogs.toString(), "here is the dog")
                    Picasso.get().load(theDogs.body()!!.message).into(theDogImageView)
//                    var mediaPlayer = MediaPlayer.create(this@MainActivity, raw.sound_file_1)
//                    mediaPlayer.start()
                }




           }


        }
    }
}