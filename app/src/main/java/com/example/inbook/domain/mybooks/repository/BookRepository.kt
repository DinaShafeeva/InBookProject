package com.example.inbook.domain.mybooks.repository

import com.example.inbook.domain.mybooks.models.Book
import io.reactivex.Observable
import io.reactivex.Single

interface BookRepository {
    fun getBook(id: Int): Single<Book>
}