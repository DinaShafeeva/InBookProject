package com.example.inbook.di.modules

import com.example.inbook.data.api.ApiFactory
import com.example.inbook.data.api.GoogleBooksApi
import com.example.inbook.di.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
class ApiFactoryModule {

    @AppScope
    @Provides
    fun provideApiFactory() = ApiFactory()

    @AppScope
    @Provides
    fun provideCharacterApiService(apiFactory: ApiFactory): GoogleBooksApi = apiFactory.googleBooksApi

}