package com.example.androidlab5.task3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.androidlab5.databinding.ActivityMain1Binding

class CoroutineDownload : AppCompatActivity() {
    private lateinit var binding: ActivityMain1Binding
    private lateinit var viewModel: CoroutineDownloadViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(CoroutineDownloadViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        if (!viewModel.isDownloaded) viewModel.download(binding.imageView)
    }

    override fun onStop() {
        super.onStop()
        viewModel.coroutine.cancel()
        if (viewModel.coroutine.isCancelled)
            viewModel.isDownloaded = false
    }
}