package com.example.inbook.data.profile.di.modules

import androidx.lifecycle.ViewModel
import com.example.inbook.app.profile.vm.ProfileViewModel
import com.example.inbook.data.profile.ProfileRepositopyImpl
import com.example.inbook.data.profile.di.scopes.ProfileScope
import com.example.inbook.di.ViewModelKey
import com.example.inbook.domain.profile.ProfileRepository
import com.example.inbook.domain.profile.interactors.ProfileInteractor
import com.example.inbook.domain.profile.interactors.ProfileInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ProfileModule {

    @ProfileScope
    @Provides
    fun provideProfileInteractor(profileInteractor: ProfileInteractorImpl)
            : ProfileInteractor = profileInteractor

    @ProfileScope
    @Provides
    fun provideProfileRepository(bookRepository: ProfileRepositopyImpl)
            : ProfileRepository = bookRepository

    @ProfileScope
    @Provides
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun provideProfileViewModel(profileInteractor: ProfileInteractor): ViewModel {
        return ProfileViewModel(
            profileInteractor
        )
    }
}