package com.example.inbook.di

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }
}