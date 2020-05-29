package com.example.inbook.data.profile

import android.util.Log
import com.example.inbook.data.dao.BookDao
import com.example.inbook.domain.mybooks.models.Book
import com.example.inbook.domain.profile.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileRepositopyImpl@Inject constructor(
    private val bookDao: BookDao
): ProfileRepository {
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun getName(): String {
        return mAuth.currentUser?.displayName ?: mAuth.currentUser?.email ?: "name"
    }

    override fun getBooksCount(): String {
        var list: List<Book> = ArrayList<Book>()
        val newBook= bookDao.getReadBooks().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    list = it
                },
                {
                        error -> Log.e("Error" , error.toString())
                })
        return list.size.toString()
    }

    override fun getWantToReadBookList(): Maybe<List<Book>> {
        return bookDao.getWantToReadBooks()
    }

    override fun getLikedList(): Maybe<List<Book>> {
        return bookDao.getLikedBooks()
    }
}