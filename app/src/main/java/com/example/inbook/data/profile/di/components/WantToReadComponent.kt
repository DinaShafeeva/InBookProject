package com.example.inbook.data.profile.di.components

import com.example.inbook.app.profile.ProfileFragment
import com.example.inbook.app.profile.wantread.WantToReadListFragment
import com.example.inbook.data.profile.di.modules.ProfileModule
import com.example.inbook.data.profile.di.modules.WantToReadModule
import com.example.inbook.data.profile.di.scopes.ProfileScope
import com.example.inbook.data.profile.di.scopes.WantToReadScope
import dagger.Subcomponent

@WantToReadScope
@Subcomponent(modules = [WantToReadModule::class])
interface WantToReadComponent {
    fun inject(wantToReadListFragment: WantToReadListFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): WantToReadComponent
    }
}