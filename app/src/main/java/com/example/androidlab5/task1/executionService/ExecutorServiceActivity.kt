package com.example.androidlab5.task1.executionService

import com.example.androidlab5.App
import com.example.androidlab5.R
import com.example.androidlab5.task1.BaseActivity

class ExecutorServiceActivity : BaseActivity() {
    @Volatile
    private var started = true
    private var instanse = App.instance

    override fun onStart() {
        super.onStart()
        started = true
        launchRunnable()
    }

    override fun onStop() {
        super.onStop()
        started = false
    }

    private fun launchRunnable() {
        instanse.executor.execute {
            var time = System.currentTimeMillis()
            while (started) {
                Thread.sleep(10)
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