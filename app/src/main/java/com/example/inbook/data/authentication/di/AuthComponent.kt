package com.example.inbook.data.authentication.di

import com.example.inbook.app.authentication.AuthFragment
import com.example.inbook.app.authentication.vm.AuthViewModel
import com.example.inbook.di.modules.ViewModelModule
import dagger.Subcomponent

@AuthScope
@Subcomponent(modules = [AuthModule::class])
interface AuthComponent {
    fun inject(authFragment: AuthFragment)

    @Subcomponent.Builder
    interface Builder{
        fun build(): AuthComponent
    }
}
