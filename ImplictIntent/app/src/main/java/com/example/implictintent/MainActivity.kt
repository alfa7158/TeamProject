package com.example.implictintent

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import androidx.annotation.IntegerRes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // the code below does the sharing from an application to another one
        val message:EditText = findViewById(R.id.message_text_field)
        val sendButton:Button = findViewById(R.id.send_button)
        val showMapButton:Button = findViewById(R.id.showMap_button)
        val showContextButton:Button = findViewById(R.id.showContext_button)
        val dailerButton:Button = findViewById(R.id.keyboardButton)
        sendButton.setOnClickListener(){
            //we creating an intent
            val sendIntent = Intent().apply {
                // we doing an action
                action = Intent.ACTION_SEND
                // to send the message to another application
                putExtra(Intent.EXTRA_TEXT, message.text.toString())
                // we specifying the type of text
                type = "text/plain"
                // starting the activity and passing it out intent
            }     // below we check to see if the activity is empty, throw exception
                try {
                    startActivity(sendIntent)
                }catch (e:ActivityNotFoundException){

                    //Handle Exception
                }

        }

        // here we creating another button to show the the loction
        showMapButton.setOnClickListener(){
            // here we are passing uri from google maps
            val gmapsIntentURI = Uri.parse("https://www.google.com/maps/place/" +
                    "Imam+AbdulRahman+Bin+Faisal+University/@26.3928049,50.1903986,17z/" +
                    "data=!3m1!4b1!4m5!3m4!1s0x3e49ef85c961edaf:0x7b2db98f2941c78c!8m2!3d26." +
                    "3928001!4d50.1925873")
            // again we create another intent for the second button
            val mapIntent = Intent(Intent.ACTION_VIEW, gmapsIntentURI)
            /// here we are are setting the package
            mapIntent.setPackage("com.google.android.apps.maps")

            startActivity(mapIntent)

        }
        // here we created another button to show the context in the phone
        showContextButton.setOnClickListener(){
            //here we creating intent to share or pass context
        val contextIntent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)

            startActivity(contextIntent)


        }

        dailerButton.setOnClickListener(){
            val myNumber = Uri.parse("tel: 054087222")
            val dialerIntent = Intent(Intent.ACTION_DIAL,myNumber )
            startActivity(dialerIntent)



        }


    }
}