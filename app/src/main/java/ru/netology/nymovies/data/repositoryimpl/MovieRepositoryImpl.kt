package ru.netology.nymovies.data.repositoryimpl

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ru.netology.nymovies.data.datamodel.Movies
import ru.netology.nymovies.data.network.ApiService
import ru.netology.nymovies.domain.errors.ApiException
import ru.netology.nymovies.domain.errors.NetWorkException
import ru.netology.nymovies.domain.errors.UnknownException
import ru.netology.nymovies.domain.repository.MovieRepository
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.*
import ru.netology.nymovies.presentation.paging.MoviesPagingSource

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {

    private val listData = mutableListOf<Movies>()

    private val _movies = MutableLiveData<List<Movies>>()
    val movies: MutableLiveData<List<Movies>>
        get() = _movies

    override val data: Flow<PagingData<Movies>> = Pager(
        config = PagingConfig(pageSize = 5, enablePlaceholders = false),
        pagingSourceFactory = { MoviesPagingSource(apiService) },
    ).flow

    override suspend fun getAll() {
        try {
            val response = apiService.getAll()
            val movies =
                response.body()?.results ?: throw ApiException(response.code(), response.message())
            for (movie in movies) {
                listData.add(movie)
            }
            _movies.value = listData
        } catch (e: IOException) {
            throw NetWorkException
        } catch (e: Exception) {
            throw UnknownException
        }
    }

}