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

    override fun getBooks(): List<Book> {
        return repository.getBooks()
    }

    override fun like(value: Book?) {
        repository.like(value)
    }

    override fun addBook(book: Book?) {
        repository.addBook(book)
    }

    override fun isBookWasRead(value: Book?): Boolean {
        return repository.isBookWasRead(value)
    }
}