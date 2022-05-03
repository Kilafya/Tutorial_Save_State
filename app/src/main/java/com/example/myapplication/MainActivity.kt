package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import kotlin.random.Random
import android.view.View


class MainActivity : AppCompatActivity() {

private lateinit var mViewBinding: ActivityMainBinding

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       mViewBinding = ActivityMainBinding.inflate(layoutInflater)
       with (mViewBinding) {
           setContentView(root)
           btnIncrement.setOnClickListener { increment() }
           btnColor.setOnClickListener { setRandomColor() }
           btnVisibility.setOnClickListener { setVisibility() }
           btnNext.setOnClickListener { goNextActivity() }
       }
    }

    private fun increment() {
        val value = mViewBinding.tv.text.toString().toInt()
        mViewBinding.tv.text = (value + 1).toString()
    }

    private fun setRandomColor() {
        val color = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        mViewBinding.tv.setTextColor(color)
    }

    private fun setVisibility() {
        with (mViewBinding) {
            if (tv.visibility == View.VISIBLE) {
                tv.visibility = View.INVISIBLE
                btnVisibility.text = "Visible"
            } else {
                tv.visibility = View.VISIBLE
                btnVisibility.text = "Invisible"
            }
        }
    }

    private fun goNextActivity() {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }
}