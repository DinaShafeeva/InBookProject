package com.example.inbook.data.mybooks.di

import androidx.lifecycle.ViewModel
import com.example.inbook.app.mybooks.vm.BookViewModel
import com.example.inbook.data.mybooks.BookRepository
import com.example.inbook.data.mybooks.BookServiceImpl
import com.example.inbook.data.mybooks.di.scopes.MyBooksScope
import com.example.inbook.domain.mybooks.BookInteractor
import dagger.Provides
import dagger.multibindings.IntoMap

class BooksModule {

    @MyBooksScope
    @Provides
    fun provideBookService(): BookServiceImpl = BookServiceImpl()


    @MyBooksScope
    @Provides
    fun provideRepository(): BookRepository = BookRepository()
}