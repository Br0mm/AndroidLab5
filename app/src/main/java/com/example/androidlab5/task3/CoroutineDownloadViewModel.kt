package com.example.androidlab5.task3

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.net.URL

class CoroutineDownloadViewModel : ViewModel() {
    @Volatile
    var isDownloaded = false
    lateinit var coroutine : Job

    companion object {
        private const val IMAGE = "https://memepedia.ru/wp-content/uploads/2021/07/never-gonna-give-you-up.jpg"
    }

    fun download(imageView: ImageView) {
        coroutine = CoroutineScope(Dispatchers.IO).launch {
            try {
                isDownloaded = true
                val url = URL(IMAGE)
                val mIconVal = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                withContext(Dispatchers.Main) {
                    imageView.setImageBitmap(mIconVal)
                }
            } catch (e: Exception) {
                isDownloaded = false
            }
        }
    }
}