package com.example.myapplication

        import android.graphics.Color
        import androidx.appcompat.app.AppCompatActivity
        import android.os.Bundle
        import android.view.View
        import androidx.core.content.ContextCompat
        import com.example.myapplication.databinding.ActivityMain3Binding
        import java.io.Serializable
        import kotlin.random.Random

        class MainActivity3 : AppCompatActivity() {
            private lateinit var mViewBinding: ActivityMain3Binding
            private lateinit var state: State

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                mViewBinding = ActivityMain3Binding.inflate(layoutInflater)

                with (mViewBinding) {
                    setContentView(root)
                    btnIncrement3.setOnClickListener { increment() }
                    btnColor3.setOnClickListener { setRandomColor() }
                    btnVisibility3.setOnClickListener { setVisibility() }
                }

                state = if (savedInstanceState == null) {
                    State(counterColor = ContextCompat.getColor(this, R.color.purple_700))
                } else {
                    savedInstanceState.getSerializable(KEY_STATE) as State
                }

                renderState()
            }

            override fun onSaveInstanceState(outState: Bundle) {
                super.onSaveInstanceState(outState)
                outState.putSerializable(KEY_STATE, state)
            }

            private fun renderState() {
                with (mViewBinding) {
                    tv3.text = state.counterValue.toString()
                    tv3.setTextColor(state.counterColor)
                    tv3.visibility = if (state.counterIsVisible) View.VISIBLE else View.INVISIBLE
                    btnVisibility3.text = if (!state.counterIsVisible) "Visible" else "Invisible"
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

            class State(
                var counterValue: Int = 0,
                var counterColor: Int = 0,
                var counterIsVisible: Boolean = true
            ) : Serializable

            companion object {
                const val KEY_STATE = "ACTIVITY_STATE"
            }
        }