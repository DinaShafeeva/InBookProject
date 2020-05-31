package com.example.inbook.data.profile

import android.util.Log
import com.example.inbook.data.dao.BookDao
import com.example.inbook.domain.models.Book
import com.example.inbook.domain.profile.ProfileRepository
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileRepositopyImpl@Inject constructor(
    private val bookDao: BookDao
): ProfileRepository {

    override fun getBooksCount(): String {
        var list: List<Book> = ArrayList<Book>()
        val newBook= bookDao.getReadBooks().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    list = it
                    Log.d("ListReadCount", list.toString())
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

    override fun getReadBooksList(): Maybe<List<Book>> {
        return bookDao.getReadBooks()
    }

    override fun getLikedBooksCount(): String {
        var list: List<Book> = ArrayList<Book>()
        var count: Int = 0
        val newBook= bookDao.getReadBooks().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    list = it
                    count = it.size
                    Log.d("ListLikedCount", list.toString())
                },
                {
                        error -> Log.e("Error" , error.toString())
                })
        return count.toString()
    }
}
