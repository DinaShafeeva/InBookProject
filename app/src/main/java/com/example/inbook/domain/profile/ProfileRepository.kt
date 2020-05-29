package com.example.inbook.domain.profile

import com.example.inbook.domain.mybooks.models.Book
import io.reactivex.Maybe

interface ProfileRepository {
    fun getName(): String
    fun getBooksCount(): String
    fun getWantToReadBookList(): Maybe<List<Book>>
    fun getLikedList(): Maybe<List<Book>>
}