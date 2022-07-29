package ru.netology.nymovies.domain.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.netology.nymovies.R
import ru.netology.nymovies.data.datamodel.Movies

object ImageUtils {

    fun loadImage(imageView: ImageView, movie: Movies) {
        Glide.with(imageView)
            .load(movie.multimedia?.src)
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.ic_baseline_downloading_24)
            .timeout(10_000)
            .into(imageView)
    }

}