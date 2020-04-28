package com.example.inbook.data.mybooks.di.modules

import androidx.lifecycle.ViewModel
import com.example.inbook.app.mybooks.vm.BookViewModel
import com.example.inbook.data.mybooks.BookRepositoryImpl
import com.example.inbook.data.mybooks.BookServiceImpl
import com.example.inbook.di.ViewModelKey
import com.example.inbook.data.mybooks.di.scopes.BookScope
import com.example.inbook.data.mybooks.di.scopes.MyBooksScope
import com.example.inbook.domain.mybooks.interactor.BookInteractor
import com.example.inbook.domain.mybooks.interactor.BookInteractorImpl
import com.example.inbook.domain.mybooks.repository.BookRepository
import com.example.inbook.domain.mybooks.services.BookService
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class BookModule {

    @BookScope
    @Provides
    fun provideBookInteractor(bookInteractor: BookInteractorImpl)
            : BookInteractor = bookInteractor

    @BookScope
    @Provides
    fun provideBookRepository(bookRepository: BookRepositoryImpl)
            : BookRepository = bookRepository

    @BookScope
    @Provides
   fun provideBookService() = BookServiceImpl()

    @BookScope
    @Provides
    @IntoMap
    @ViewModelKey(BookViewModel::class)
    fun provideBooksViewModel(bookInteractor: BookInteractor, bookService: BookServiceImpl): ViewModel {
        return BookViewModel(
            bookInteractor,
            bookService
        )
    }
}