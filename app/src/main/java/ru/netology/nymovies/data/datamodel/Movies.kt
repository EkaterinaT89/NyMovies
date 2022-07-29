package ru.netology.nymovies.data.datamodel

import com.google.gson.annotations.SerializedName

data class Movies(
    var id: Int,
    @SerializedName("display_title")
    val displayTitle: String,
    @SerializedName("mpaa_rating")
    val mpaaRaiting: String,
    @SerializedName("critics_pick")
    val criticsPick: Int,
    val byline: String,
    val headline: String,
    @SerializedName("summary_short")
    val summaryShort: String,
    @SerializedName("publication_date")
    val publicationDate: String,
    @SerializedName("opening_date")
    val openingDate: String? = null,
    @SerializedName("date_updated")
    val dateUpdated: String,
    val link: Link,
    val multimedia: Multimedia? = null
)