package com.example.inbook.domain.profile.interactors

import com.example.inbook.domain.models.Book
import io.reactivex.Maybe

interface ProfileInteractor {
    fun getBooksCount(): String
    fun getWantToReadBookList(): Maybe<List<Book>>
    fun getLikedList(): Maybe<List<Book>>
    fun getLikedBooksCount(): String
    fun getReadBooksList(): Maybe<List<Book>>
}