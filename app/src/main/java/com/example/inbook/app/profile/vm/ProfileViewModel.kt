package com.example.inbook.app.profile.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inbook.domain.mybooks.models.Book
import com.example.inbook.domain.profile.interactors.ProfileInteractor
import com.example.inbook.domain.response.BookResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProfileViewModel( val interactor: ProfileInteractor): ViewModel() {
    var result: MutableLiveData<String> = MutableLiveData()
    private val bookLiveData: MutableLiveData<List<Book>> = MutableLiveData()


    fun getName(): LiveData<String>{
        result.value = interactor.getName()
        return result
    }

    fun getBooksCount(): LiveData<String> {
        result.value = interactor.getBooksCount()
        return result
    }

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

    fun getLikedList(): LiveData<List<Book>> {
        var data: Disposable = interactor.getLikedList().subscribeOn(Schedulers.io())
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