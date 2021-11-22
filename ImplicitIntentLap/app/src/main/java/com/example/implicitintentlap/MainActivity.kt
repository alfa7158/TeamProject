package com.example.implicitintentlap

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import java.net.URI
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val open:Button = findViewById(R.id.openButton)
        val editUrl:EditText = findViewById(R.id.EditTextURL)

        open.setOnClickListener(){
println(editUrl.text)
            val URLIntent = Intent(Intent.ACTION_VIEW,  Uri.parse(editUrl.text.toString()))
            startActivity(URLIntent)
        }
    }
}