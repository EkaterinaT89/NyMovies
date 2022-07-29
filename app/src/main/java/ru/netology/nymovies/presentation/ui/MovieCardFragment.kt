package ru.netology.nymovies.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.nymovies.databinding.FragmentMovieCardBinding

@AndroidEntryPoint
class MovieCardFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentMovieCardBinding.inflate(
            inflater,
            container,
            false
        )


        return binding.root
    }

}