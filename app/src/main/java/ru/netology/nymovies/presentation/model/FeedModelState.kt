package ru.netology.nymovies.presentation.model

data class FeedModelState(
    val loading: Boolean = false,
    val error: Boolean = false
)