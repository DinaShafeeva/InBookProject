package com.example.inbook.domain.mybooks.interactor

import com.example.inbook.domain.mybooks.repository.BookRepository
import com.example.inbook.domain.mybooks.models.Book
import io.reactivex.Single
import javax.inject.Inject

class BookInteractorImpl @Inject
constructor(
    private val repository: BookRepository
): BookInteractor {
    override fun getBook(id: Int): Single<Book> {
        return repository.getBook(id)
    }
}