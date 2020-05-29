package com.example.inbook.domain.mybooks.interactor

import com.example.inbook.data.mybooks.BookRepositoryImpl
import com.example.inbook.domain.mybooks.models.Book
import com.example.inbook.domain.response.BookResponse
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

interface BookInteractor{
   // fun getBook(id: Int): Single<BookResponse>
    fun getBookByName(name:String): Single<BookResponse>
    fun getBooks(): Maybe<List<Book>>
    fun like(value: Book?)
    fun addBook(book: Book?)
    fun isBookWasRead(id: String?): Boolean
    fun getBookByNameFromDB(name: String): Maybe<Book>
}
