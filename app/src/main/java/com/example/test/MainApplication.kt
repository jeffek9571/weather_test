package com.example.test

import android.app.Application

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        private lateinit var instance : MainApplication
        fun instance() = instance
    }
}