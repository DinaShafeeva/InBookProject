package com.example.inbook.domain.profile.interactors

import com.example.inbook.domain.models.Book
import com.example.inbook.domain.profile.ProfileRepository
import io.reactivex.Maybe
import javax.inject.Inject

class ProfileInteractorImpl @Inject
constructor(
    private val repository: ProfileRepository
): ProfileInteractor {


    override fun getBooksCount(): String {
        return repository.getBooksCount()
    }

    override fun getWantToReadBookList(): Maybe<List<Book>> {
        return repository.getWantToReadBookList()
    }

    override fun getLikedList(): Maybe<List<Book>> {
        return repository.getLikedList()
    }

    override fun getLikedBooksCount(): String {
        return repository.getLikedBooksCount()
    }

    override fun getReadBooksList(): Maybe<List<Book>> {
        return repository.getReadBooksList()
    }

}