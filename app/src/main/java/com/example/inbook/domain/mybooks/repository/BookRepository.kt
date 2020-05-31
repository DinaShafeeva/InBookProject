package com.example.inbook.domain.mybooks.repository

import com.example.inbook.domain.models.Book
import com.example.inbook.domain.response.BookResponse
import io.reactivex.Maybe
import io.reactivex.Single

interface BookRepository {
    //fun getBook(id: Int): Single<BookResponse>
    fun getBookByName(name:String): Single<BookResponse>
    fun getBooks(): Maybe<List<Book>>
    fun like(book: Book?)
    fun addBook(book: Book?)
    fun isBookWasRead(id: String?): Boolean
    fun getBookByNameFromDB(name: String): Maybe<Book>
    fun getReadBooks(): Maybe<List<Book>>
    fun addQuote
                (text: String, nameOfBook: String)
}