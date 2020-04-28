package com.example.inbook.data.mybooks

import com.example.inbook.data.api.GoogleBooksApi
import com.example.inbook.domain.mybooks.repository.BookRepository
import com.example.inbook.domain.mybooks.models.Book
import io.reactivex.Single
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val apiService: GoogleBooksApi
): BookRepository {
    lateinit var book : Single<Book>

    override fun getBook(id: Int): Single<Book> {
        //TODO: достает данные
        return book
    }
}