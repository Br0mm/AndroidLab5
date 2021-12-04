package com.example.androidlab5.task1.coroutine

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.androidlab5.R
import com.example.androidlab5.task1.BaseActivity
import kotlinx.coroutines.delay

class CoroutineActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenResumed {
            var time = System.currentTimeMillis()
            while (true) {
                delay(10)
                if (System.currentTimeMillis() >= time) {
                    textSecondsElapsed.post {
                        textSecondsElapsed.text =
                            getString(R.string.seconds_elapsed, secondsElapsed++)
                    }
                    time += 1000
                }
            }
        }
    }
}