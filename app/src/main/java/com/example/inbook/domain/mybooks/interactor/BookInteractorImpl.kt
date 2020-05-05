package com.example.inbook.domain.mybooks.interactor

import com.example.inbook.domain.mybooks.repository.BookRepository
import com.example.inbook.domain.mybooks.models.Book
import com.example.inbook.domain.response.BookResponse
import io.reactivex.Single
import javax.inject.Inject

class BookInteractorImpl @Inject
constructor(
    private val repository: BookRepository
): BookInteractor {
    override fun getBook(id: Int): Single<BookResponse> {
        return repository.getBook(id)
    }

    override fun getBookByName(name: String): Single<BookResponse> {
        return repository.getBookByName(name)
    }
}