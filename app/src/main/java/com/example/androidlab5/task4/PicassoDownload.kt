package com.example.androidlab5.task4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlab5.databinding.ActivityMain1Binding
import com.squareup.picasso.Picasso

class PicassoDownload : AppCompatActivity() {
    private lateinit var binding: ActivityMain1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        Picasso.with(this)
            .load(IMAGE)
            .into(binding.imageView)
    }

    companion object {
        const val IMAGE =
            "https://memepedia.ru/wp-content/uploads/2021/07/never-gonna-give-you-up.jpg"
    }
}