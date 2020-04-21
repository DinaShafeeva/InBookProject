package com.example.inbook.data.mybooks.di

import com.example.inbook.app.mybooks.MyBooksFragment
import com.example.inbook.data.mybooks.BookRepository
import com.example.inbook.data.mybooks.di.scopes.MyBooksScope
import com.example.inbook.domain.mybooks.services.BookService
import dagger.Subcomponent

@MyBooksScope
@Subcomponent(modules = [BooksModule::class])
interface BooksComponent {

    fun inject(myBooksFragment: MyBooksFragment)
    fun getRepository(): BookRepository
    fun getService(): BookService

    @Subcomponent.Builder
    interface Builder {
        fun booksModule(booksModule: BooksModule): Builder
        fun build(): BooksComponent
    }
}