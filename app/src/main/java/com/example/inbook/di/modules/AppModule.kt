package com.example.inbook.di.modules

import android.app.Application
import android.content.Context
import android.content.Intent
import com.example.inbook.di.App
import com.example.inbook.di.scopes.AppScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule() {
    @AppScope
    @Provides
    fun provideContext(application: App): Context = application.applicationContext
}
