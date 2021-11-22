package com.example.e_commerce.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.e_commerce.R
import com.example.e_commerce.database.RoomServiceRepository
import com.example.e_commerce.databinding.ActivitySplahBinding
import com.example.e_commerce.repostries.ApiSeviceRepository
import com.example.e_commerce.view.main.MainActivity

class Splah : AppCompatActivity() {
    private lateinit var binding:ActivitySplahBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiSeviceRepository.init(this)
        RoomServiceRepository.init(this)
        binding = ActivitySplahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val logo:TextView = findViewById(R.id.textView)
        logo.animate().rotation(100f).alpha(0f)
            .translationXBy(1000f)
            .translationYBy(-1000f).
            translationYBy(-1000f).
            translationX(500f).rotationY(300f)
            .duration = 7000
            binding.motionLayout.setTransitionListener(object : MotionLayout.TransitionListener{
                override fun onTransitionStarted(
                    motionLayout: MotionLayout?,
                    startId: Int,
                    endId: Int
                ) {
                }

                override fun onTransitionChange(
                    motionLayout: MotionLayout?,
                    startId: Int,
                    endId: Int,
                    progress: Float
                ) {
                }

                override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                  val intent = Intent(this@Splah,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                override fun onTransitionTrigger(
                    motionLayout: MotionLayout?,
                    triggerId: Int,
                    positive: Boolean,
                    progress: Float
                ) {
                }


            })
    }
}