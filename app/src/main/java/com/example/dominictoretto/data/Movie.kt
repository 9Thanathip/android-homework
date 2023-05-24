package com.example.dominictoretto.data

data class Movie(
    val ststus: String = "",
    val data: data? = null,
)

data class MovieInfo(
    val ststus: String = "",
    val data: data? = null,
)

data class data(
    val title: String = "",
    val image: String = "",
    val content: List<content>? = null,
    val type: String = ""
)

data class content(
    val title: String = "",
    val infos: List<infos>? = null,
)

data class infos(
    val name: String = "",
    val is_episode: Boolean = false,
    val image: String = "",
)

