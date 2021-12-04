package com.example.androidlab5.task1.thread

import com.example.androidlab5.R
import com.example.androidlab5.task1.BaseActivity

class ThreadActivity : BaseActivity() {
    @Volatile
    private var started = true
    private lateinit var backgroundThread: Thread

    override fun onStart() {
        super.onStart()
        started = true
        backgroundThread = createThread()
        backgroundThread.start()
    }

    override fun onStop() {
        super.onStop()
        started = false
    }

    private fun createThread(): Thread  {
        return Thread {
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