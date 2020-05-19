package com.example.inbook.app.mybooks.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inbook.data.dao.BookDao
import com.example.inbook.domain.mybooks.interactor.BookInteractor
import com.example.inbook.domain.mybooks.models.Book
import com.example.inbook.domain.mybooks.services.BookService

class ListBooksViewModel(val interactor: BookInteractor): ViewModel() {

    fun getBooks(): LiveData<List<Book>> {
        val books: LiveData<List<Book>> = MutableLiveData(interactor.getBooks())
        return books
    }
}
