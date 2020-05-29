package com.example.inbook.data.profile.di.components

import com.example.inbook.app.profile.ProfileFragment
import com.example.inbook.app.profile.fragmentlists.LikedListFragment
import com.example.inbook.app.profile.fragmentlists.WantToReadListFragment
import com.example.inbook.data.profile.di.modules.ProfileModule
import com.example.inbook.data.profile.di.scopes.ProfileScope
import dagger.Subcomponent

@ProfileScope
@Subcomponent(modules = [ProfileModule::class])
interface ProfileComponent {
    fun inject(profileFragment: ProfileFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ProfileComponent
    }
}