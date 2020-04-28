package com.example.inbook.data.mybooks.di.components

import com.example.inbook.app.mybooks.BookFragment
import com.example.inbook.app.mybooks.MyBooksFragment
import com.example.inbook.data.mybooks.di.modules.BookModule
import com.example.inbook.data.mybooks.di.scopes.BookScope
import com.example.inbook.data.mybooks.di.scopes.MyBooksScope
import dagger.Subcomponent

//@MyBooksScope
//@Subcomponent(modules = [MyBooksModule::class])
//interface MyBooksComponent {
//    fun inject(myBooksFragment: MyBooksFragment)
//
//    @Subcomponent.Builder
//    interface Builder {
//        fun build(): MyBooksComponent
//    }
//}