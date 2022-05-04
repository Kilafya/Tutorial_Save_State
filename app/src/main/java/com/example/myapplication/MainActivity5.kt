package com.example.myapplication

    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import androidx.activity.viewModels
    import androidx.core.content.ContextCompat
    import androidx.lifecycle.Observer
    import com.example.myapplication.databinding.ActivityMain5Binding
    import android.view.View

    class MainActivity5 : AppCompatActivity() {
        private lateinit var mViewBinding: ActivityMain5Binding
        private val viewModel: MainActivity5ViewModel by viewModels()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            mViewBinding = ActivityMain5Binding.inflate(layoutInflater)
            with (mViewBinding) {
                setContentView(root)
                btnIncrement5.setOnClickListener { viewModel.increment() }
                btnColor5.setOnClickListener { viewModel.setRandomColor() }
                btnVisibility5.setOnClickListener { viewModel.setVisibility() }
            }

            if (viewModel.state.value == null) {
                viewModel.initState(
                    MainActivity5ViewModel.State(
                    counterColor = ContextCompat.getColor(this, R.color.purple_700)
                ))
            }

            viewModel.state.observe(this, Observer {
                renderState()
            })
        }

        private fun renderState() {
            with (mViewBinding) {
                viewModel.state.value?.let {
                    tv5.text = it.counterValue.toString()
                    tv5.setTextColor(it.counterColor)
                    tv5.visibility = if (it.counterIsVisible) View.VISIBLE else View.INVISIBLE
                    btnVisibility5.text = if (!it.counterIsVisible) "Visible" else "Invisible"
                }
            }
        }
    }