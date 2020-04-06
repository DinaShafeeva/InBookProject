package com.example.inbook.data

import com.example.inbook.domain.Book
import io.reactivex.Observable

class BookRepository() {

    lateinit var book : Observable<Book>

    fun getBook(id: Int): Observable<Book> {
        //TODO: достает данные
        return book
    }
}