package com.example.inbook.data.dao

import androidx.room.*
import com.example.inbook.domain.mybooks.models.Book
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface BookDao {
    @Insert
    fun insert(book: Book)

    @Update
    fun update(book: Book)

    @Delete
    fun delete(book: Book)

    @Query("SELECT * FROM book")
    fun getAllBooks(): Maybe<List<Book>>

    @Query("SELECT * FROM book WHERE id = :id")
    fun getBook(id: String): Maybe<Book>

    @Query("SELECT * FROM book WHERE status = 1")
    fun getWantToReadBooks(): Maybe<List<Book>>
}
