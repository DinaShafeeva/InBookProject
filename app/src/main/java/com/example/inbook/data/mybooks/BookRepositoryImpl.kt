package com.example.inbook.data.mybooks

import com.example.inbook.data.api.GoogleBooksApi
import com.example.inbook.domain.mybooks.repository.BookRepository
import com.example.inbook.domain.mybooks.models.Book
import com.example.inbook.domain.response.BookResponse
import io.reactivex.Single
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val apiService: GoogleBooksApi
): BookRepository {
    lateinit var book : Single<BookResponse>

    override fun getBook(id: Int): Single<BookResponse> {
        //TODO: достает данные
        return book
    }

    override fun getBookByName(name: String): Single<BookResponse> {
        return apiService.searchBook(name)
    }
}