package com.example.galleryimageintentapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import java.net.URI

private val requestCode = 2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectPhotoButton: Button = findViewById(R.id.selectPhotoButton)
        val selectedImage:ImageView = findViewById(R.id.selectedgalleryImageView)

        selectPhotoButton.setOnClickListener(){
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(intent,requestCode)

        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        val selectedImage:ImageView = findViewById(R.id.selectedgalleryImageView)

        if(requestCode == requestCode && resultCode == RESULT_OK){
            val Image = data?.data
            selectedImage.setImageURI(Image)

            }
        }


    }

/*below is the way to select an image using bit map*/
//package com.example.galleryimageintentapp
//
//import android.content.Intent
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.provider.MediaStore
//import android.widget.Button
//import android.widget.ImageView
//import java.net.URI
//
//private val requestCode = 2
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val selectPhotoButton: Button = findViewById(R.id.selectPhotoButton)
//        val selectedImage:ImageView = findViewById(R.id.selectedgalleryImageView)
//
//        selectPhotoButton.setOnClickListener(){
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.type = "image/*"
//            startActivityForResult(intent,requestCode)
//
//        }
//
//    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//
//        super.onActivityResult(requestCode, resultCode, data)
//        val selectedImage:ImageView = findViewById(R.id.selectedgalleryImageView)
//
//        if(requestCode == requestCode && resultCode == RESULT_OK){
//            val Image = MediaStore.Images.Media.getBitmap(this.contentResolver,data?.data)
//            selectedImage.setImageBitmap(Image)
//
//        }
//    }
//