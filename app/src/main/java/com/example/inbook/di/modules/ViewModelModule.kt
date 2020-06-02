package com.example.inbook.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inbook.app.authentication.vm.AuthViewModel
import com.example.inbook.app.mybooks.vm.BookViewModel
import com.example.inbook.di.ViewModelFactory
import com.example.inbook.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule  {
    @Binds
     fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
