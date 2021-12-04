package com.example.androidlab5.task1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlab5.R

abstract class BaseActivity : AppCompatActivity() {
    @Volatile
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    lateinit var sharedPref: SharedPreferences

    companion object {
        const val SECONDS = "seconds"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onStart() {
        super.onStart()
        sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        secondsElapsed = sharedPref.getInt(SECONDS, 0)
    }

    override fun onStop() {
        super.onStop()
        sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putInt(SECONDS, secondsElapsed)
            apply()
        }
    }
}