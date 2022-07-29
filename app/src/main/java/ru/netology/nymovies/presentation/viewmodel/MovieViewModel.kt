package ru.netology.nymovies.presentation.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.netology.nymovies.data.datamodel.Movies
import ru.netology.nymovies.domain.repository.MovieRepository
import ru.netology.nymovies.domain.utils.ActionType
import ru.netology.nymovies.presentation.model.FeedModelState
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _dataState = MutableLiveData<FeedModelState>()
    val dataState: LiveData<FeedModelState>
        get() = _dataState

    private var lastAction: ActionType? = null

    val data: Flow<PagingData<Movies>> = repository.data

    init {
        getAll()
    }

    fun tryAgain() {
        when (lastAction) {
            ActionType.GETALL -> retryGetAll()
            else -> getAll()
        }
    }

    fun getAll() {
        viewModelScope.launch {
            lastAction = ActionType.GETALL
            try {
                _dataState.postValue(FeedModelState(loading = true))
                repository.getAll()
                _dataState.value = FeedModelState()
            } catch (e: Exception) {
                _dataState.value = FeedModelState(error = true)
            }
        }
    }

    private fun retryGetAll() {
        getAll()
    }

}