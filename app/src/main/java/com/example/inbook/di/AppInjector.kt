package com.example.inbook.di

import com.example.inbook.data.authentication.di.AuthComponent
import com.example.inbook.di.components.AppComponent
import com.example.inbook.data.mybooks.di.components.BookComponent
import com.example.inbook.data.mybooks.di.components.ListBooksComponent
import com.example.inbook.data.profile.di.components.ProfileComponent
import com.example.inbook.data.profile.di.components.WantToReadComponent
import com.example.inbook.di.components.DaggerAppComponent

object AppInjector {
    lateinit var appComponent: AppComponent
    private var bookComponent: BookComponent? = null
    private var profileComponent: ProfileComponent? = null
    private var authComponent: AuthComponent? = null
    private var listBooksComponent: ListBooksComponent? = null
    private var wantToReadComponent: WantToReadComponent? = null

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

    fun plusProfileComponent(): ProfileComponent = profileComponent
        ?: appComponent.profileComponent()
            .build().also {
                profileComponent = it
            }

    fun clearProfileComponent() {
        profileComponent = null
    }

    fun plusAuthComponent(): AuthComponent = authComponent
        ?: appComponent.authComponent()
            .build().also {
                authComponent = it
            }

    fun clearAuthComponent() {
        authComponent = null
    }

    fun plusListBooksComponent(): ListBooksComponent = listBooksComponent
        ?: appComponent.listBooksComponent()
            .build().also {
                listBooksComponent = it
            }

    fun clearMyBooksComponent() {
        listBooksComponent = null
    }

    fun plusWantToReadComponent(): WantToReadComponent = wantToReadComponent
        ?: appComponent.wantToReadComponent()
            .build().also {
                wantToReadComponent = it
            }

    fun clearWantToReadComponent() {
        wantToReadComponent = null
    }
}
