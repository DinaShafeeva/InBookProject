package com.example.inbook.data.dao.di

import android.content.Context
import com.example.inbook.data.dao.BookDao
import com.example.inbook.data.dao.DataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideUserDao(appDatabase: DataBase): BookDao {
        return appDatabase.getBookDao()
    }
}
