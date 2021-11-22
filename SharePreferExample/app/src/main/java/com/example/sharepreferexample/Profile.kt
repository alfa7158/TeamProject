package com.example.sharepreferexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharepreferexample.databinding.ActivityProfileBinding

class Profile : AppCompatActivity() {
    private lateinit var binding:ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("userName")
        binding.userTextView.text = username



    }
}