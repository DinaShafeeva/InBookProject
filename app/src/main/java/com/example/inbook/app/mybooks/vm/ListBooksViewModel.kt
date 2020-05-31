package com.example.inbook.app.mybooks.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inbook.domain.mybooks.interactor.BookInteractor
import com.example.inbook.domain.models.Book
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListBooksViewModel(val interactor: BookInteractor): ViewModel() {
    var books: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks(): LiveData<List<Book>> {
         (interactor.getReadBooks().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                books.value = it
        }, {
                    it.printStackTrace()
                }))
        Log.d("ListInVM", books.value.toString())
        return books
    }
}
