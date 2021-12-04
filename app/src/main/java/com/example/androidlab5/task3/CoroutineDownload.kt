package com.example.androidlab5.task3

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlab5.databinding.ActivityMain1Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class CoroutineDownload : AppCompatActivity() {
    @Volatile
    private var isDownloaded = false
    private lateinit var binding: ActivityMain1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!isDownloaded) CoroutineScope(Dispatchers.IO).launch {
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