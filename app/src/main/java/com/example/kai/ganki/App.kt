package com.example.kai.ganki

import android.app.Application
import com.example.kai.ganki.utils.Common

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Common.with(this)
    }
}