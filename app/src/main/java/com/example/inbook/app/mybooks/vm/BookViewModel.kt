package com.example.inbook.app.mybooks.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inbook.domain.mybooks.interactor.BookInteractor
import com.example.inbook.domain.models.Book
import com.example.inbook.domain.mybooks.services.BookService
import com.example.inbook.domain.response.BookResponse
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers


class BookViewModel( val interactor: BookInteractor,
                     val service: BookService): ViewModel() {

    private val bookLiveData: MutableLiveData<BookResponse> = MutableLiveData()
    private var book: MutableLiveData<Book> = MutableLiveData()
    private var bookMutableLiveData: MutableLiveData<Book> = MutableLiveData()

    private fun getBookMutableLiveDataByName(name:String): MutableLiveData<Book> {
        var data: Disposable = interactor.getBookByName(name).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    data -> bookLiveData.value = data
                Log.d("Book: " , data.toString())

                val book: Book = Book(bookLiveData.value?.items?.get(0)?.id ?:"-1",
                    bookLiveData.value?.items?.get(0)?.volumeInfo?.title ?: "name",
                    bookLiveData.value?.items?.get(0)?.volumeInfo?.authors?.get(0) ?: "author",
                    false,
                    bookLiveData.value?.items?.get(0)?.volumeInfo?.description ?: "description",
                    bookLiveData.value?.items?.get(0)?.volumeInfo?.imageLinks?.thumbnail ?: "image")
                Log.d("book = ", book.nameOfBook)
                 bookMutableLiveData = MutableLiveData(book)

            },
                {
                        error -> Log.e("Error" , error.toString())
                })
        return bookMutableLiveData
    }

    fun getBook(name: String): LiveData<Book> = getBookMutableLiveDataByName(name)

    fun getBookFromDB(name: String): LiveData<Book>{
     //   book.value?.nameOfBook = "null"
        (interactor.getBookByNameFromDB(name).subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread()).subscribe({
             book.value = it
         }, {
                 it.printStackTrace()
             }))

        if (!book.value?.nameOfBook.equals(null)) {
            Log.d("ListInVM", book.value.toString())
            return book
        } else return getBook(name)
    }

    fun isBookWasRead(id: String?): Boolean = interactor.isBookWasRead(id)

    fun addBook(book: LiveData<Book>) {
        Completable.fromAction(object : Action {
            @Throws(Exception::class)
            override fun run() {
                interactor.addBook(book.value)
                Log.d("db", "insert")
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

    fun like(book: LiveData<Book>) = interactor.like(book.value)

    fun wantToRead(book: MutableLiveData<Book>){
        Completable.fromAction(object : Action {
            @Throws(Exception::class)
            override fun run() {
                book.value?.status = 1
                interactor.addBook(book.value)
                Log.d("db", "insertToWantToRead")
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