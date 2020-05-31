package com.example.inbook.data.profile.di.modules

import androidx.lifecycle.ViewModel
import com.example.inbook.app.profile.vm.LikedViewModel
import com.example.inbook.app.profile.vm.ProfileViewModel
import com.example.inbook.data.profile.ProfileRepositopyImpl
import com.example.inbook.data.profile.di.scopes.LikedScope
import com.example.inbook.di.ViewModelKey
import com.example.inbook.domain.profile.ProfileRepository
import com.example.inbook.domain.profile.interactors.ProfileInteractor
import com.example.inbook.domain.profile.interactors.ProfileInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class LikedModule {
    @LikedScope
    @Provides
    fun provideProfileInteractor(profileInteractor: ProfileInteractorImpl)
            : ProfileInteractor = profileInteractor

    @LikedScope
    @Provides
    fun provideProfileRepository(bookRepository: ProfileRepositopyImpl)
            : ProfileRepository = bookRepository

    @LikedScope
    @Provides
    @IntoMap
    @ViewModelKey(LikedViewModel::class)
    fun provideLikedViewModel(profileInteractor: ProfileInteractor): ViewModel {
        return LikedViewModel(
            profileInteractor
        )
    }
}
