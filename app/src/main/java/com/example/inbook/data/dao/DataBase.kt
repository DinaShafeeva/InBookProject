package com.example.inbook.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inbook.domain.mybooks.models.Book

@Database(version = 4, entities = [Book::class])
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