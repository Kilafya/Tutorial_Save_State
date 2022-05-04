package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityMain4Binding
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

class MainActivity4 : AppCompatActivity() {
    private lateinit var mViewBinding: ActivityMain4Binding
    private lateinit var state: State

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMain4Binding.inflate(layoutInflater)

        with (mViewBinding) {
            setContentView(root)
            btnIncrement4.setOnClickListener { increment() }
            btnColor4.setOnClickListener { setRandomColor() }
            btnVisibility4.setOnClickListener { setVisibility() }
            btnNext4.setOnClickListener { goNextActivity() }
        }

        state = savedInstanceState?.getParcelable(KEY_STATE) ?:
            State(counterColor = ContextCompat.getColor(this, R.color.purple_700))

        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, state)
    }

    private fun goNextActivity() {
        val intent = Intent(this, MainActivity5::class.java)
        startActivity(intent)
    }

    private fun renderState() {
        with (mViewBinding) {
            tv4.text = state.counterValue.toString()
            tv4.setTextColor(state.counterColor)
            tv4.visibility = if (state.counterIsVisible) View.VISIBLE else View.INVISIBLE
            btnVisibility4.text = if (!state.counterIsVisible) "Visible" else "Invisible"
        }
    }

    private fun increment() {
        ++state.counterValue
        renderState()
    }

    private fun setRandomColor() {
        state.counterColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun setVisibility() {
        state.counterIsVisible = !state.counterIsVisible
        renderState()
    }

    @Parcelize
    class State(
        var counterValue: Int = 0,
        var counterColor: Int = 0,
        var counterIsVisible: Boolean = true
    ) : Parcelable

    companion object {
        const val KEY_STATE = "ACTIVITY_STATE"
    }
}