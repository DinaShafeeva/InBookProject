package com.example.inbook.data.mybooks.di.components

import com.example.inbook.app.mybooks.BookFragment
import com.example.inbook.app.mybooks.MyBooksFragment
import com.example.inbook.app.search.SearchBookFragment
import com.example.inbook.data.mybooks.di.modules.BookModule
import com.example.inbook.data.mybooks.di.modules.ListBooksModule
import com.example.inbook.data.mybooks.di.scopes.BookScope
import dagger.Subcomponent

@BookScope
@Subcomponent(modules = [ListBooksModule::class])
interface ListBooksComponent {
    fun inject(myBooksFragment: MyBooksFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ListBooksComponent
    }
}