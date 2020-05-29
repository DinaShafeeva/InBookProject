package com.example.inbook.domain.profile.interactors

import com.example.inbook.domain.mybooks.models.Book
import io.reactivex.Maybe
import java.util.*

interface ProfileInteractor {
    fun getName(): String
    fun getBooksCount(): String
    fun getWantToReadBookList(): Maybe<List<Book>>
    fun getLikedList(): Maybe<List<Book>>
}