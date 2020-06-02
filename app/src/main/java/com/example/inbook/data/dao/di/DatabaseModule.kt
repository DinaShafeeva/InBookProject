package com.example.inbook.data.dao.di

import android.content.Context
import androidx.room.Room
import com.example.inbook.data.dao.BookDao
import com.example.inbook.data.dao.DataBase
import com.example.inbook.di.scopes.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @AppScope
    fun provideAppDatabase(context: Context): DataBase {
        return DataBase.get(context)
    }

    @Provides
    @AppScope
    fun provideUserDao(appDatabase: DataBase): BookDao {
        return appDatabase.getBookDao()
    }
}
