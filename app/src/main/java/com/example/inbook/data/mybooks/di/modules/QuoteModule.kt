package com.example.inbook.data.mybooks.di.modules

import androidx.lifecycle.ViewModel
import com.example.inbook.app.mybooks.vm.BookViewModel
import com.example.inbook.app.mybooks.vm.QuoteViewModel
import com.example.inbook.data.mybooks.BookRepositoryImpl
import com.example.inbook.data.mybooks.di.scopes.BookScope
import com.example.inbook.data.mybooks.di.scopes.QuoteScope
import com.example.inbook.di.ViewModelKey
import com.example.inbook.domain.mybooks.interactor.BookInteractor
import com.example.inbook.domain.mybooks.interactor.BookInteractorImpl
import com.example.inbook.domain.mybooks.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class QuoteModule {
    @QuoteScope
    @Provides
    fun provideBookInteractor(bookInteractor: BookInteractorImpl)
            : BookInteractor = bookInteractor

    @QuoteScope
    @Provides
    fun provideBookRepository(bookRepository: BookRepositoryImpl)
            : BookRepository = bookRepository

    @QuoteScope
    @Provides
    @IntoMap
    @ViewModelKey(QuoteViewModel::class)
    fun provideQuoteViewModel(bookInteractor: BookInteractor): ViewModel {
        return QuoteViewModel(
            bookInteractor
        )
    }
}
