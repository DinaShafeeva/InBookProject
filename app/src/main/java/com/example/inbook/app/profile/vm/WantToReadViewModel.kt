package com.example.inbook.app.profile.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inbook.domain.models.Book
import com.example.inbook.domain.profile.interactors.ProfileInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WantToReadViewModel ( val interactor: ProfileInteractor): ViewModel() {
        private val bookLiveData: MutableLiveData<List<Book>> = MutableLiveData()

        fun getWantToReadBookList(): LiveData<List<Book>> {
            var data: Disposable = interactor.getWantToReadBookList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                        data -> bookLiveData.value = data
                    Log.d("Book: " , data.toString())
                },
                    {
                            error -> Log.e("Error" , error.toString())
                    })
            return bookLiveData
        }
    }
