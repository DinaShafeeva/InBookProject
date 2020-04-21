package com.example.inbook.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Singleton
    @Provides
    fun provideContext(application: App): Context = application.applicationContext
}