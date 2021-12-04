package com.example.androidlab5

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    private var cores = Runtime.getRuntime().availableProcessors()
    var executor: ExecutorService = Executors.newFixedThreadPool(cores)

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}