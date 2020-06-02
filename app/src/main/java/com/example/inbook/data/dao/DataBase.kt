package com.example.inbook.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.inbook.domain.models.Book
import com.example.inbook.domain.models.QuotesConverter

@Database(version = 5, entities = [Book::class])
@TypeConverters(QuotesConverter::class)
abstract class DataBase: RoomDatabase() {
    abstract fun getBookDao(): BookDao
    companion object {
        private var instance: DataBase? = null
        private const val DATABASE_NAME = "app.db"

        @Synchronized
        fun get(context: Context): DataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java, DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}
