package com.example.inbook.app.mybooks.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inbook.domain.models.Book
import com.example.inbook.domain.mybooks.interactor.BookInteractor
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class QuoteViewModel(val interactor: BookInteractor): ViewModel() {
    private var book: MutableLiveData<Book> = MutableLiveData()

    fun getBook(name: String): LiveData<Book>{
        (interactor.getBookByNameFromDB(name).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                book.value = it
            }, {
                it.printStackTrace()
            }))
            return book
    }

    fun addQuote(text: String, nameOfBook: String) {
        Completable.fromAction(object : Action {
            @Throws(Exception::class)
            override fun run() {
                interactor.addQuote(text, nameOfBook)
                Log.d("db", "insertQuote")
            }
        }).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                }
                override fun onComplete() {
                }
                override fun onError(e: Throwable) {
                    Log.e("Error" , "data is not available", e)
                }
            })
    }
}
