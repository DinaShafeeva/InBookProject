package com.example.inbook.domain.mybooks.interactor

import com.example.inbook.domain.mybooks.repository.BookRepository
import com.example.inbook.domain.models.Book
import com.example.inbook.domain.response.BookResponse
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class BookInteractorImpl @Inject
constructor(
    private val repository: BookRepository
): BookInteractor {
//    override fun getBook(id: Int): Single<BookResponse> {
//        return repository.getBook(id)
//    }

    override fun getBookByName(name: String): Single<BookResponse> {
        return repository.getBookByName(name)
    }

    override fun getBooks(): Maybe<List<Book>> {
        return repository.getBooks()
    }

    override fun getReadBooks(): Maybe<List<Book>> {
        return repository.getReadBooks()
    }

    override fun like(value: Book?) {
        repository.like(value)
    }

    override fun addBook(book: Book?) {
        repository.addBook(book)
    }

    override fun isBookWasRead(id: String?): Boolean {
        return repository.isBookWasRead(id)
    }

    override fun getBookByNameFromDB(name: String): Maybe<Book> {
        return repository.getBookByNameFromDB(name)
    }

    override fun addQuote(text: String, nameOfBook: String) {
        return repository.addQuote(text, nameOfBook)
    }
}
