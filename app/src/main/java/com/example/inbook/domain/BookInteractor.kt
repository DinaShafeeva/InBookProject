package com.example.inbook.domain

import com.example.inbook.data.BookRepository
import io.reactivex.Observable

class BookInteractor {
    private val repository = BookRepository()

    fun getBook(id: Int): Observable<Book> {
        return repository.getBook(id)
    }
}