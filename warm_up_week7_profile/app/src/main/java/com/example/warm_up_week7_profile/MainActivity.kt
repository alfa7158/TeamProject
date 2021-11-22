package com.example.warm_up_week7_profile

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val callButton:Button = findViewById(R.id.callMeButton)
        val emailButton:Button = findViewById(R.id.emailMeButton)

        callButton.setOnClickListener(){
            val myNumber = Uri.parse("tel: 0540879909")
            val dialerIntent = Intent(Intent.ACTION_DIAL,myNumber )
            startActivity(dialerIntent)



        }

        emailButton.setOnClickListener(){
            val myEmail = Uri.parse("mailto: alfarajabdullah5@gami.com")
            val contextIntent = Intent(Intent.ACTION_SENDTO,myEmail)

            startActivity(contextIntent)


        }


    }
}