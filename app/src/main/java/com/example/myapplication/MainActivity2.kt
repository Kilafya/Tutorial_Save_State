package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.random.Random
import android.view.View
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityMain2Binding
import kotlin.properties.Delegates.notNull


class MainActivity2 : AppCompatActivity() {

    private lateinit var mViewBinding: ActivityMain2Binding
    private var counterValue by notNull<Int>()
    private var counterColor by notNull<Int>()
    private var counterIsVisible by notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMain2Binding.inflate(layoutInflater)
        with (mViewBinding) {
            setContentView(root)
            btnIncrement2.setOnClickListener { increment() }
            btnColor2.setOnClickListener { setRandomColor() }
            btnVisibility2.setOnClickListener { setVisibility() }
            btnNext2.setOnClickListener { goNextActivity() }
        }

        if (savedInstanceState == null) {
            counterValue = 0
            counterColor = ContextCompat.getColor(this, R.color.purple_700)
            counterIsVisible = true
        } else {
            counterValue = savedInstanceState.getInt(KEY_VALUE)
            counterColor = savedInstanceState.getInt(KEY_COLOR)
            counterIsVisible = savedInstanceState.getBoolean(KEY_IS_VISIBLE)
        }

        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_VALUE, counterValue)
        outState.putInt(KEY_COLOR, counterColor)
        outState.putBoolean(KEY_IS_VISIBLE, counterIsVisible)
    }

    private fun renderState() {
        with (mViewBinding) {
            tv2.text = counterValue.toString()
            tv2.setTextColor(counterColor)
            tv2.visibility = if (counterIsVisible) View.VISIBLE else View.INVISIBLE
            btnVisibility2.text = if (!counterIsVisible) "Visible" else "Invisible"
        }
    }

    private fun increment() {
        ++counterValue
        renderState()
    }

    private fun setRandomColor() {
        counterColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun setVisibility() {
        counterIsVisible = !counterIsVisible
        renderState()
    }

    private fun goNextActivity() {
        val intent = Intent(this, MainActivity3::class.java)
        startActivity(intent)
    }

    companion object {
        const val KEY_VALUE = "VALUE"
        const val KEY_COLOR = "COLOR"
        const val KEY_IS_VISIBLE = "IS_VISIBLE"
    }
}