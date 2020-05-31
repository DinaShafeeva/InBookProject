package com.example.inbook.domain.mybooks.services

import androidx.lifecycle.LiveData
import com.example.inbook.domain.models.Book

interface BookService {
    fun getMyBooks(): ArrayList<LiveData<Book>>
    fun addBook(book: LiveData<Book>)
    fun isBookWasRead(book: LiveData<Book>): Boolean
    fun like(book: LiveData<Book>)
}
