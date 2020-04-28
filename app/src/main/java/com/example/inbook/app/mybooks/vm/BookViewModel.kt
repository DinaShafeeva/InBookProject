package com.example.inbook.app.mybooks.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inbook.data.mybooks.BookServiceImpl
import com.example.inbook.domain.mybooks.interactor.BookInteractor
import com.example.inbook.domain.mybooks.models.Book
import com.example.inbook.domain.mybooks.services.BookService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BookViewModel( val interactor: BookInteractor,
                     val service: BookService): ViewModel() {

    private val bookLiveData: MutableLiveData<Book> = MutableLiveData()

    private fun  getBookMutableLiveData(id: Int): MutableLiveData<Book> {
        var data: Disposable = interactor.getBook(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    data -> bookLiveData.value = data
                Log.d("Book: " , data.toString())
            },
                {
                        error -> Log.e("Error" , error.toString())
                })
        return bookLiveData
    }
    fun getBook(id: Int): LiveData<Book> = getBookMutableLiveData(id)

    private var myBooksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
}