package com.example.inbook.di.components

import com.example.inbook.data.authentication.di.AuthComponent
import com.example.inbook.data.dao.di.DatabaseModule
import com.example.inbook.data.mybooks.di.components.BookComponent
import com.example.inbook.data.mybooks.di.components.ListBooksComponent
import com.example.inbook.data.mybooks.di.components.QuoteComponent
import com.example.inbook.data.profile.di.components.LikedComponent
import com.example.inbook.data.profile.di.components.ProfileComponent
import com.example.inbook.data.profile.di.components.QuoteListComponent
import com.example.inbook.data.profile.di.components.WantToReadComponent
import com.example.inbook.di.App
import com.example.inbook.di.modules.ApiFactoryModule
import com.example.inbook.di.modules.AppModule
import com.example.inbook.di.modules.ViewModelModule
import com.example.inbook.di.scopes.AppScope
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, ApiFactoryModule::class, ViewModelModule::class, DatabaseModule::class])
interface AppComponent {

    fun bookComponent(): BookComponent.Builder
    fun profileComponent(): ProfileComponent.Builder
    fun authComponent(): AuthComponent.Builder
    fun listBooksComponent(): ListBooksComponent.Builder
    fun wantToReadComponent(): WantToReadComponent.Builder
    fun likedComponent(): LikedComponent.Builder
    fun quoteComponent(): QuoteComponent.Builder
    fun quoteListComponent(): QuoteListComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun build(): AppComponent
    }
}
