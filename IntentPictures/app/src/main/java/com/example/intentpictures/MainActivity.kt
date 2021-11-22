package com.example.intentpictures

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "MainActivity"
private val CMERA_INTENT_REQUEST_CODE = 1
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /// to create a camera intent we need to add:
        ///to add in the manifest :  <uses-feature android:name="android.hardware.camera" android:required="true"></uses-feature>

        /// secondly:
        ///Getting the context of Button

        val cameraButton:Button = findViewById(R.id.camera_button)

        cameraButton.setOnClickListener(){
            dispatchCameraIntent()

        }


    }

    // we are creating function for the intent instead of writing the code directly inside the button
    /// Action image capture is the action to launch the camera of the phone
    private fun dispatchCameraIntent(){
        //first way to do it
        ///val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        try{
//            /// the line below is to get us the result of the camera
//            /// in other words, get us the picture that is captured
//            startActivityForResult(cameraIntent,CMERA_INTENT_REQUEST_CODE)
//        }catch (e:ActivityNotFoundException){
//            ///Log error
//            /// into Database or email = Developer
//            // Toast, dialog, navigate to any other fragment user
//
//
//        }
        //second way
        // here in the first line we creating an intent but we scope functions
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                /// below we are checking if the image is created
                val photoFile:File? = try{
                    createImageFile()
                }catch (e:IOException){
                    null
                }
                photoFile?.also {
                    ///
                    val photoUri:Uri = FileProvider.getUriForFile(this,"com.example.intentpictures",it)

                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                    startActivityForResult(takePictureIntent,CMERA_INTENT_REQUEST_CODE)
                }
            }
        }






    }
        /// collecting the result and display
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
            val theImage:ImageView = findViewById(R.id.imageViewCamera)

            if(requestCode == CMERA_INTENT_REQUEST_CODE && resultCode == RESULT_OK){
                /// ("data") is the key for the camera
                /// before when we used to get values, we had to put a key,
                /// but here we have a default key from the android studio
//                val bitmapImage = data?.extras?.get("data") as Bitmap
//                theImage.setImageBitmap(bitmapImage)
                BitmapFactory.decodeFile(currentphotoPath)?.also {
                    bitmap ->
                    theImage.setImageBitmap(bitmap)

                }
            }


        }

    /////////////////////////////functions for saving the result///////////////////////////////
    // two line to the manifest
    ///    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"></uses-permission>
    //    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
    ///
    lateinit var currentphotoPath:String
    private fun createImageFile():File{

        // Create an image name
        val timestamp:String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        // below we are we want the pictures directory
        val storageDir:File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        // below it returns created image file with date and time as well as the type
        return File.createTempFile(
            "JPEG_${timestamp}"
            ,".jpg",
            storageDir /* directory name*/

        ).apply {
            currentphotoPath = absolutePath
        }
    }
}