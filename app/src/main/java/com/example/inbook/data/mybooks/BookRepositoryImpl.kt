package com.example.inbook.data.mybooks

import android.util.Log
import com.example.inbook.data.api.GoogleBooksApi
import com.example.inbook.data.dao.BookDao
import com.example.inbook.domain.mybooks.repository.BookRepository
import com.example.inbook.domain.mybooks.models.Book
import com.example.inbook.domain.response.BookResponse
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val apiService: GoogleBooksApi,
    private val bookDao: BookDao
): BookRepository {
    lateinit var book : Single<BookResponse>

    override fun getBook(id: Int): Single<BookResponse> {

        return book
    }

    override fun getBookByName(name: String): Single<BookResponse> {
        return apiService.searchBook(name)
    }

    override fun getBooks(): List<Book> {
        var list: List<Book> = ArrayList<Book>()
        val newBook= bookDao.getAllBooks().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                     list = it
                },
                {
                        error -> Log.e("Error" , error.toString())
                })
        return list
    }

    override fun like(book: Book?) {
        val newBook = bookDao.getBook(book?.id ?: "0").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    it.like = true
                    Completable.fromAction(object : Action {
                        @Throws(Exception::class)
                        override fun run() {
                            bookDao.update(it)
                            Log.d("Update", "successful")
                        }
                    }).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io()).subscribe(object : CompletableObserver {
                            override fun onSubscribe(d: Disposable) {
                            }
                            override fun onComplete() {
                            }
                            override fun onError(e: Throwable) {
                                Log.e("Error" , "data is not available")
                            }
                        })
                },
                {
                        error -> Log.e("Error" , error.toString())
                })
    }

    override fun addBook(book: Book?) {
        if (book != null) {
            bookDao.insert(book)
        }
    }

    override fun isBookWasRead(book: Book?): Boolean {
        var result: Boolean = false
        val newBook = bookDao.getBook(book?.id ?: "0").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    result= (it.status == 0)
                },
                {
                        error -> Log.e("Error" , error.toString())
                })
        return result
    }
}
