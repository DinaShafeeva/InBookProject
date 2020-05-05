package com.example.inbook.domain.mybooks.interactor

import com.example.inbook.data.mybooks.BookRepositoryImpl
import com.example.inbook.domain.mybooks.models.Book
import com.example.inbook.domain.response.BookResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

interface BookInteractor{
    fun getBook(id: Int): Single<BookResponse>
    fun getBookByName(name:String): Single<BookResponse>
}
