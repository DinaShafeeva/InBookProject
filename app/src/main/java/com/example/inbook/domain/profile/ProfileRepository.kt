package com.example.inbook.domain.profile

import com.example.inbook.domain.models.Book
import io.reactivex.Maybe

interface ProfileRepository {
    fun getBooksCount(): String
    fun getWantToReadBookList(): Maybe<List<Book>>
    fun getLikedList(): Maybe<List<Book>>
    fun getReadBooksList(): Maybe<List<Book>>
    fun getLikedBooksCount(): String
}