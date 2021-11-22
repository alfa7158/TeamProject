package com.example.sharepreferexample

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharepreferexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // the first parameter is the name of the file
        /// the second parameter is the context
        /// blow we created a file
        val sharedPreference = getSharedPreferences("Login", Context.MODE_PRIVATE)
        val PreferenceEditor = sharedPreference.edit()

        if(sharedPreference.getBoolean("Status",false)){

            val intent = Intent(this, Profile::class.java)
            // name is the key and the userName is the value we want to send
            val userName = sharedPreference.getString("userName","")
            intent.putExtra("userName", userName)
            startActivity(intent)
            finish()
        }

        binding.loginButtob.setOnClickListener(){

            val userName = binding.UserNameEditText.text.toString()
            val userPassword = binding.passwordEditText.text.toString()

            if(userName.isNotEmpty()&&userPassword.isNotEmpty()){
                ////////////////////////////////////////////////////
                PreferenceEditor.putString("userName",userName)
                PreferenceEditor.putBoolean("Status", true)
                PreferenceEditor.commit()
                /////////////////////////////////////////////////////

                val intent = Intent(this, Profile::class.java)
                // name is the key and the userName is the value we want to send
                intent.putExtra("userName", userName)
                startActivity(intent)
                finish()
            }
        }
    }



}