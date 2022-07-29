package ru.netology.nymovies.data.network

import okhttp3.logging.HttpLoggingInterceptor
import ru.netology.nymovies.BuildConfig

fun loggingInterceptor() = HttpLoggingInterceptor()
    .apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }