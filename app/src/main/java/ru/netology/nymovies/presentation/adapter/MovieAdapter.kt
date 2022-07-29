package ru.netology.nymovies.presentation.adapter

import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.saket.bettermovementmethod.BetterLinkMovementMethod
import ru.netology.nymovies.data.datamodel.Movies
import ru.netology.nymovies.databinding.FragmentMovieCardBinding
import ru.netology.nymovies.domain.utils.ImageUtils

interface OnInteractionListener {
    fun onLink(url: String)
}

class MovieAdapter(private val onInteractionListener: OnInteractionListener) :
    PagingDataAdapter<Movies, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            FragmentMovieCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MovieViewHolder(onInteractionListener, binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }

    }

    class MovieViewHolder(
        private val onInteractionListener: OnInteractionListener,
        private val binding: FragmentMovieCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movies: Movies) {

            binding.apply {

                if (movies.multimedia != null) {
                    ImageUtils.loadImage(movieImage, movies)
                } else {
                    movieImage.visibility = View.GONE
                }

                displayTitle.text = movies.displayTitle
                byline.text = movies.byline
                publicationDate.text = movies.publicationDate
                headLine.text = movies.headline
                suggestedLinkText.text = movies.link.suggestedLinkText

                urlForArticle.text = movies.link.url
                urlForArticle.movementMethod = BetterLinkMovementMethod.getInstance()
                BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, urlForArticle)
                    .setOnLinkClickListener { _, url ->
                        onInteractionListener.onLink(url)
                        true
                    }

                summaryShort.text = movies.summaryShort
                mpaaRating.text = movies.mpaaRaiting
                criticsPick.text = movies.criticsPick.toString()

                if (movies.openingDate == null) {
                    openingDate.visibility = View.INVISIBLE
                } else {
                    openingDate.text = movies.openingDate
                }

                dateUpdated.text = movies.dateUpdated

            }

        }

    }

    class MovieDiffCallback : DiffUtil.ItemCallback<Movies>() {
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem.displayTitle == newItem.displayTitle
        }

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem == newItem
        }

    }

}