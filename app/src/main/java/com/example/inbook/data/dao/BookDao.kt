package com.example.inbook.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.inbook.domain.mybooks.models.Book

@Dao
interface BookDao {
    @Insert
    fun insert(book: Book)

    @Delete
    fun delete(book: Book)

    @Query("SELECT * FROM book")
    fun getAllBooks(): List<Book?>?
}
