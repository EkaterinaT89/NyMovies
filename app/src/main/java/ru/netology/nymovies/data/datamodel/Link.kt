package ru.netology.nymovies.data.datamodel

import com.google.gson.annotations.SerializedName

data class Link(
    val type: String,
    val url: String,
    @SerializedName("suggested_link_text")
    val suggestedLinkText: String
    )