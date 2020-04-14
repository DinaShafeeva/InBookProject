package com.example.inbook.domain.mybooks

import com.example.inbook.data.mybooks.BookRepository
import io.reactivex.Observable

class BookInteractor {
    private val repository = BookRepository()

    fun getBook(id: Int): Observable<Book> {
        return repository.getBook(id)
    }
}