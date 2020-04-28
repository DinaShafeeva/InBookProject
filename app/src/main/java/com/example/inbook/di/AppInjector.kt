package com.example.inbook.di

import com.example.inbook.data.authentication.di.AuthComponent
import com.example.inbook.di.components.AppComponent
import com.example.inbook.data.mybooks.di.components.BookComponent
import com.example.inbook.di.components.DaggerAppComponent

object AppInjector {
    lateinit var appComponent: AppComponent
    private var bookComponent: BookComponent? = null
    private var authComponent: AuthComponent? = null
  //  private var myBooksComponent: MyBooksComponent? = null

    fun init(app: App) {
        appComponent = DaggerAppComponent.builder()
            .application(app)
            .build()
    }
    fun plusBookComponent(): BookComponent = bookComponent
        ?: appComponent.bookComponent()
            .build().also {
                bookComponent = it
            }

    fun clearBookComponent() {
        bookComponent = null
    }

    fun plusAuthComponent(): AuthComponent = authComponent
        ?: appComponent.authComponent()
            .build().also {
                authComponent = it
            }

    fun clearAuthComponent() {
        authComponent = null
    }

//    fun plusMyBooksComponent(): MyBooksComponent = myBooksComponent
//        ?: appComponent.myBooksComponent()
//            .build().also {
//                myBooksComponent = it
//            }
//
//    fun clearMyBooksComponent() {
//        myBooksComponent = null
//    }
}
