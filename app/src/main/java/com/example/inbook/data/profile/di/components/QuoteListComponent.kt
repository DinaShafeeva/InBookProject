package com.example.inbook.data.profile.di.components

import com.example.inbook.app.profile.fragmentlists.QuotesListFragment
import com.example.inbook.data.profile.di.modules.QuoteListModule
import com.example.inbook.data.profile.di.scopes.QuoteListScope
import dagger.Subcomponent

@QuoteListScope
@Subcomponent(modules = [QuoteListModule::class])
interface QuoteListComponent {
        fun inject(quotesFragment: QuotesListFragment)

        @Subcomponent.Builder
        interface Builder {
            fun build(): QuoteListComponent
        }
}
