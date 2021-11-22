package com.example.recivingintent

import android.content.Intent
import android.net.Uri
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.log
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//         * Intent filter includes in manifest:

//         * 1- action: name = action_send
//         * 2- Data : name = name of type(MIME)
//         * 3- CATEGORY: The default
        //Example in the manifest:
        ///note:  /* is a restriction for the data type
//         *<action android:name="android.intent.action.SEND"></action>
//         * data android:mimeType="text/*"></data>
//        <category android:name="android.intent.category.DEFAULT"></category>
//        <data android:mimeType="image/*"></data>



        val picView:ImageView = findViewById(R.id.imageViewA)
        val theTextView:TextView = findViewById(R.id.textViewA)

        //Below we are receiving the intent from another app:
        val reciviedIntent = intent
       val  receivedAction = reciviedIntent.action
        Log.d(receivedAction.toString(),"reciviedIntent")
        val recivedType = reciviedIntent.type
        Log.d(recivedType.toString(), "recivedType")
        /// We are checking for the values we will be receiving from the other app
        if(recivedType != null && recivedType!!.startsWith("text/")){

            picView.setVisibility(View.GONE)
            // Gets the recivedText
            val recivedText = reciviedIntent.getStringExtra(Intent.EXTRA_TEXT)
            // we are checking for the value we are getting if it is null or not
            if(recivedText !=null){
                theTextView.setText(recivedText)
            }
        }
        // here we are checking for the image we are receiving if it is null or not
        else if(recivedType !=null && recivedType!!.startsWith("image/")){

            theTextView.setVisibility(View.GONE)
            //Gets the recived URI of the image

            val recivedURI: Uri? = reciviedIntent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as Uri?

            if(recivedURI != null){
                picView.setImageURI(recivedURI)

            }
        }


    }
}