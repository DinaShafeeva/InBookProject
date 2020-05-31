package com.example.inbook.app.profile.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inbook.domain.authentication.Authentication
import com.example.inbook.domain.profile.interactors.ProfileInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProfileViewModel( val interactor: ProfileInteractor,
                        val authentication: Authentication): ViewModel() {
    var result: MutableLiveData<String> = MutableLiveData()
    var readCount: MutableLiveData<String> = MutableLiveData()
    var likedCount: MutableLiveData<String> = MutableLiveData()


    fun getBooksCount(): LiveData<String> {
        var data: Disposable = interactor.getReadBooksList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    data -> readCount.value = data.size.toString()
                Log.d("Book: " , data.toString())
            },
                {
                        error -> Log.e("Error" , error.toString())
                })
        return readCount
    }

    fun getLikedBooksCount(): LiveData<String> {
        var data: Disposable = interactor.getLikedList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    data ->likedCount.value = data.size.toString()
                Log.d("Book: " , data.toString())
            },
                {
                        error -> Log.e("Error" , error.toString())
                })
        return likedCount
    }

    fun signOut() {
        authentication.signOut()
    }

//    fun getBooksCount(): LiveData<String> {
//        result.value = interactor.getBooksCount()
//        return result
//    }

//    fun getLikedBooksCount(): LiveData<String> {
//        result.value = interactor.getLikedBooksCount()
//        return result
//    }
}