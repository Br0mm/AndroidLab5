package com.example.androidlab5.task2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidlab5.App
import java.net.URL
import java.util.concurrent.Future

class ExecutorDownloadViewModel : ViewModel() {
    @Volatile
    var isDownloaded = false
    val image = MutableLiveData<Bitmap>()
    private val instance = App.instance
    lateinit var future: Future<*>

    companion object {
        private const val IMAGE =
            "https://memepedia.ru/wp-content/uploads/2021/07/never-gonna-give-you-up.jpg"
    }

    fun download() {
        future = instance.executor.submit {
            try {
                isDownloaded = true
                val url = URL(IMAGE)
                val mIconVal = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                image.postValue(mIconVal)
            } catch (e: Exception) {
                isDownloaded = false
            }
        }
    }
}