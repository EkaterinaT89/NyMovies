package ru.netology.nymovies.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.netology.nymovies.data.datamodel.Movies
import ru.netology.nymovies.data.network.ApiService
import ru.netology.nymovies.domain.errors.ApiException

class MoviesPagingSource(private val service: ApiService) : PagingSource<Int, Movies>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies> {
        try {
            val response = when (params) {
                is LoadParams.Refresh -> service.getAll()
                is LoadParams.Prepend -> return LoadResult.Page(
                    data = emptyList(),
                    prevKey = params.key,
                    nextKey = null
                )
                else -> {
                    service.getAll()
                }
            }

            if (!response.isSuccessful) {
                throw ApiException(response.code(), response.message())
            }
            val body = response.body()?.results ?: throw ApiException(
                response.code(),
                response.message(),
            )

            val nextKey = if (body.isEmpty()) null else body.last().id
            return LoadResult.Page(
                data = body,
                prevKey = params.key,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movies>): Int? {
        return null
    }

    override val keyReuseSupported = true

}
