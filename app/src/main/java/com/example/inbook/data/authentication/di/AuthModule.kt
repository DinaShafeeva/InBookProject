package com.example.inbook.data.authentication.di

import androidx.lifecycle.ViewModel
import com.example.inbook.app.authentication.vm.AuthViewModel
import com.example.inbook.data.authentication.AuthenticationImpl
import com.example.inbook.di.ViewModelKey
import com.example.inbook.domain.authentication.Authentication
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class AuthModule {
    @AuthScope
    @Provides
    fun provideAuthentication(authentication: AuthenticationImpl)
            : Authentication = authentication

    @AuthScope
    @Provides
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun provideAuthViewModel(authenticationImpl: AuthenticationImpl): ViewModel {
        return AuthViewModel(
            authenticationImpl
        )
    }
}
