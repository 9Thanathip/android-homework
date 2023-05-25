package com.example.dominictoretto.data

data class Movie(
    val status: String = "",
    val data: Data? = null,
)

data class MovieInfo(
    val status: String = "",
    val data: Data? = null,
)

data class Data(
    val title: String = "",
    val image: String = "",
    val content: List<Content>? = null,
    val type: String = ""
)

data class Content(
    val title: String = "",
    val infos: List<Info>? = null,
)

data class Info(
    val name: String = "",
    val is_episode: Boolean = false,
    val image: String = "",
)

