package com.example.androidlab5.task2

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.androidlab5.App
import com.example.androidlab5.databinding.ActivityMain1Binding
import java.net.URL

class ExecutorDownload : AppCompatActivity() {
    @Volatile
    private var isDownloaded = false
    private val instance = App.instance
    private lateinit var binding: ActivityMain1Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!isDownloaded) instance.executor.execute {
            download(binding.imageView)
        }
    }

    private fun download(imageView: ImageView) {
        try {
            isDownloaded = true
            val url = URL(IMAGE)
            val mIconVal = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            imageView.post {
                imageView.setImageBitmap(mIconVal)
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Unsuccessful Download", Toast.LENGTH_SHORT).show()
            isDownloaded = false
        }
    }

    companion object {
        const val IMAGE = "https://memepedia.ru/wp-content/uploads/2021/07/never-gonna-give-you-up.jpg"
    }
}