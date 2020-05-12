package com.example.inbook.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookDao::class], version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun getBookDao(): BookDao
}