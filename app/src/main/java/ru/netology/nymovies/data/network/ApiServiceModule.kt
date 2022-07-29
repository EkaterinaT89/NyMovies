package ru.netology.nymovies.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiServiceModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return retrofit(okhttp(loggingInterceptor()))
            .create(ApiService::class.java)
    }
}