package ru.netology.nymovies.presentation.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.nymovies.databinding.FragmentMovieListBinding
import ru.netology.nymovies.presentation.adapter.MovieAdapter
import ru.netology.nymovies.presentation.adapter.OnInteractionListener
import ru.netology.nymovies.presentation.viewmodel.MovieViewModel
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MovieListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentMovieListBinding.inflate(
            inflater,
            container,
            false
        )

        val movieViewModel: MovieViewModel by viewModels(
            ownerProducer = ::requireParentFragment
        )

        val moviesAdapter = MovieAdapter(object : OnInteractionListener {
            override fun onLink(url: String) {
                CustomTabsIntent.Builder()
                    .setShowTitle(true)
                    .build()
                    .launchUrl(requireContext(), Uri.parse(url))
            }

        })

        movieViewModel.dataState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                progress.isVisible = state.loading
                if (state.error) {
                    serverError.isVisible = state.error
                    serverErrorButton.setOnClickListener {
                        movieViewModel.tryAgain()
                        serverError.visibility = View.GONE
                    }
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            movieViewModel.data.collectLatest(moviesAdapter::submitData)
        }

        lifecycleScope.launchWhenCreated {
            moviesAdapter.loadStateFlow.collectLatest { state ->
                binding.swiperefresh.isRefreshing =
                    state.refresh is LoadState.Loading ||
                            state.prepend is LoadState.Loading ||
                            state.append is LoadState.Loading
            }
        }

        binding.movieContainer.adapter = moviesAdapter

        return binding.root
    }

}