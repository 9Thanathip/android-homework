package com.example.dominictoretto.data

data class Movie(
    val ststus: String,
    val data : data,
)

data class MovieInfo(
    val ststus: String,
    val data: data,
)

data class data(
    val title:String,
    val image:String,
    val content:List<content>? = null,
    val type:String
)

data class content(
    val title:String,
    val infos:List<infos>,
)

data class infos(
    val name : String,
    val is_episode:Boolean,
    val image: String,
)

