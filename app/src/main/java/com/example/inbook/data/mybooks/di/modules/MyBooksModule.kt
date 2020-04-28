package com.example.inbook.data.mybooks.di.modules

import androidx.lifecycle.ViewModel
import com.example.inbook.app.mybooks.vm.BookViewModel
import com.example.inbook.data.mybooks.BookRepositoryImpl
import com.example.inbook.data.mybooks.BookServiceImpl
import com.example.inbook.data.mybooks.di.scopes.BookScope
import com.example.inbook.data.mybooks.di.scopes.MyBooksScope
import com.example.inbook.di.ViewModelKey
import com.example.inbook.domain.mybooks.interactor.BookInteractor
import com.example.inbook.domain.mybooks.interactor.BookInteractorImpl
import com.example.inbook.domain.mybooks.repository.BookRepository
import com.example.inbook.domain.mybooks.services.BookService
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

//@Module
//class MyBooksModule {
//    @MyBooksScope
//    @Provides
//    fun provideBookService(bookService: BookServiceImpl)
//            : BookService = bookService
//
//    @MyBooksScope
//    @Provides
//    @IntoMap
//    @ViewModelKey(BookViewModel::class)
//    fun provideBooksViewModel(bookInteractor: BookInteractor): ViewModel {
//        return BookViewModel(
//            bookInteractor
//        )
//    }
//}