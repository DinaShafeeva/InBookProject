package com.example.inbook.di.components

import com.example.inbook.data.authentication.di.AuthComponent
import com.example.inbook.data.mybooks.di.components.BookComponent
import com.example.inbook.di.App
import com.example.inbook.di.modules.ApiFactoryModule
import com.example.inbook.di.modules.AppModule
import com.example.inbook.di.modules.ViewModelModule
import com.example.inbook.di.scopes.AppScope
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, ApiFactoryModule::class, ViewModelModule::class])
interface AppComponent {

//    fun getApplication(): Application
    fun bookComponent(): BookComponent.Builder
    fun authComponent(): AuthComponent.Builder
  //  fun myBooksComponent(): MyBooksComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun build(): AppComponent
    }
}
