package com.example.inbook.data.api

import com.example.inbook.domain.response.BookResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {
    @GET("/books/v1/volumes/")
    fun searchBook(@Query("q") q: String?): Single<BookResponse?>?
}