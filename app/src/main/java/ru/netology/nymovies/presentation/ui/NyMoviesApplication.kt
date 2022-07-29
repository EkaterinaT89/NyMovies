package ru.netology.nymovies.presentation.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NyMoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}