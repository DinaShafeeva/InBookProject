package com.example.inbook.app.profile.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inbook.domain.models.Book
import com.example.inbook.domain.models.Quote
import com.example.inbook.domain.profile.interactors.ProfileInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class QuoteListViewModel (val interactor: ProfileInteractor): ViewModel() {
    private val quotes: MutableLiveData<ArrayList<Quote>> = MutableLiveData()

    fun getQuotes(): LiveData<ArrayList<Quote>>{
        var data: Disposable = interactor.getReadBooksList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({ data ->
                Log.d("BookQuote: " , data.toString())
                var list: ArrayList<Quote> = ArrayList()
                data.forEach {book ->
                    book.quotes.forEach {
                        list.add(Quote(book.nameOfBook, it))
                    }
                }
                quotes.value = list
                Log.d("QuoteVM: " , quotes.value.toString())
            },
                {
                        error -> Log.e("Error" , error.toString())
                })
        return quotes
    }
}
