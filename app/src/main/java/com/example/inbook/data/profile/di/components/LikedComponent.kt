package com.example.inbook.data.profile.di.components

import com.example.inbook.app.profile.fragmentlists.LikedListFragment
import com.example.inbook.data.profile.di.modules.LikedModule
import com.example.inbook.data.profile.di.scopes.LikedScope
import dagger.Subcomponent

@LikedScope
@Subcomponent(modules = [LikedModule::class])
interface LikedComponent {
    fun inject(likedFragment: LikedListFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): LikedComponent
    }
}
