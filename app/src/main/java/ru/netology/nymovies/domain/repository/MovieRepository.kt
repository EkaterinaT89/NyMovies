package ru.netology.nymovies.domain.repository

import androidx.paging.PagingData
import ru.netology.nymovies.data.datamodel.Movies
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    val data: Flow<PagingData<Movies>>

    suspend fun getAll()

}