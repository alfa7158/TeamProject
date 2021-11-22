package com.example.wizerd_week7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.wizerd_week7.API.WeatherService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var tempTextView: TextView
    private lateinit var humidityTextView: TextView
    private lateinit var windSpeedTextView:TextView
    private lateinit var cityEditTextView: EditText
    private lateinit var weatherImageView: ImageView
    private lateinit var weatherButton:ImageButton
    private lateinit var informationLayout:LinearLayout
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherButton.setOnClickListener(){

            getWeather()
        }




    }
     fun getWeather(){
        val city = cityEditTextView.text.toString()
        if(city.isNotEmpty() && city.isNotBlank()){
            informationLayout.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
            // scope Coroutine is only concerned with the class that it is int it
            CoroutineScope(Dispatchers.IO).launch{
                try {
                    val response = WeatherService.getWeather(city)

                    withContext(Dispatchers.Main){
                        if(response.isSuccessful){

                            response.body()?.run {
                                informationLayout.visibility = View.VISIBLE
                                progressBar.visibility = View.INVISIBLE
                                tempTextView.text = "${main.temp}°"
                                humidityTextView.text = "${main.humidity}"
                                windSpeedTextView.text = wind.speed.toString()
                                //original link: http://openweathermap.org/img/wn/10d@2x.png
                                val imageUrl = "http://openweathermap.org/img/wn/${weather[0].icon}@4x.png"
                                Picasso.get().load(imageUrl).into(weatherImageView)
                                progressBar

                            }

                        }else{
                            progressBar.visibility = View.INVISIBLE

                            Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT).show()
                        }


                    }

                }catch (e:Exception){
                    ///Handel Api error here


                }
            }
        }

    }
}