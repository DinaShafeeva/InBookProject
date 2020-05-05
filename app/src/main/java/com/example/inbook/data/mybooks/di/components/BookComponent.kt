package com.example.inbook.data.mybooks.di.components

import com.example.inbook.app.mybooks.BookFragment
import com.example.inbook.app.mybooks.MyBooksFragment
import com.example.inbook.app.search.SearchBookFragment
import com.example.inbook.data.mybooks.di.modules.BookModule
import com.example.inbook.data.mybooks.di.scopes.BookScope
import dagger.Subcomponent

@BookScope
@Subcomponent(modules = [BookModule::class])
interface BookComponent {
    fun inject(bookFragment: BookFragment)
    fun inject(myBooksFragment: MyBooksFragment)
    fun inject(searchBookFragment: SearchBookFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): BookComponent
    }
}
