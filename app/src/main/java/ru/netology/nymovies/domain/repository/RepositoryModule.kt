package ru.netology.nymovies.domain.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.netology.nymovies.data.repositoryimpl.MovieRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

}