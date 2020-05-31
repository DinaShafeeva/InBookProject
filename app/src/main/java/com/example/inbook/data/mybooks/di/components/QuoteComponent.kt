package com.example.inbook.data.mybooks.di.components

import com.example.inbook.app.mybooks.BookFragment
import com.example.inbook.app.mybooks.MyBooksFragment
import com.example.inbook.app.mybooks.QuoteFragment
import com.example.inbook.app.search.SearchBookFragment
import com.example.inbook.data.mybooks.di.modules.QuoteModule
import com.example.inbook.data.mybooks.di.scopes.QuoteScope
import dagger.Subcomponent

@QuoteScope
@Subcomponent(modules = [QuoteModule::class])
interface QuoteComponent {
    fun inject(quoteFragment: QuoteFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): QuoteComponent
    }
}