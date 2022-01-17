package com.example.androidlab5.task2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.androidlab5.databinding.ActivityMain1Binding

class ExecutorDownload : AppCompatActivity() {
    private lateinit var binding: ActivityMain1Binding
    private lateinit var viewModel: ExecutorDownloadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ExecutorDownloadViewModel::class.java)
        subscribe()
    }

    override fun onStart() {
        super.onStart()
        if (!viewModel.isDownloaded) viewModel.download()
    }

    override fun onStop() {
        super.onStop()
        viewModel.future.cancel(true)
        if (viewModel.future.isCancelled)
            viewModel.isDownloaded = false
    }

    private fun subscribe() {
        viewModel.image.observe(this) {
            binding.imageView.setImageBitmap(it)
        }
    }
}