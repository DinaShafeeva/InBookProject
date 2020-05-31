package com.example.inbook.data.profile.di.modules

import androidx.lifecycle.ViewModel
import com.example.inbook.app.profile.vm.QuoteListViewModel
import com.example.inbook.data.profile.ProfileRepositopyImpl
import com.example.inbook.data.profile.di.scopes.QuoteListScope
import com.example.inbook.di.ViewModelKey
import com.example.inbook.domain.profile.ProfileRepository
import com.example.inbook.domain.profile.interactors.ProfileInteractor
import com.example.inbook.domain.profile.interactors.ProfileInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class QuoteListModule  {
    @QuoteListScope
    @Provides
    fun provideProfileInteractor(profileInteractor: ProfileInteractorImpl)
            : ProfileInteractor = profileInteractor

    @QuoteListScope
    @Provides
    fun provideProfileRepository(bookRepository: ProfileRepositopyImpl)
            : ProfileRepository = bookRepository

    @QuoteListScope
    @Provides
    @IntoMap
    @ViewModelKey(QuoteListViewModel::class)
    fun provideLikedViewModel(profileInteractor: ProfileInteractor): ViewModel {
        return QuoteListViewModel(
            profileInteractor
        )
    }
}