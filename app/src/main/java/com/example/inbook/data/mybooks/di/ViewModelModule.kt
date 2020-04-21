package com.example.inbook.data.mybooks.di

import androidx.lifecycle.ViewModel
import com.example.inbook.app.mybooks.vm.BookViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
class ViewModelModule  {

    @IntoMap
    @Provides
    @Singleton
    @ViewModelKey(BookViewModel::class)
    fun bindBookViewModel(
        mainViewModel: BookViewModel
    ): ViewModel = mainViewModel
}