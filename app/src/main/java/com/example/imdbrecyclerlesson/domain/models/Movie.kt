package com.example.imdbrecyclerlesson.domain.models

import java.io.Serializable

data class Movie(
    val id: String,
    val resultType: String,
    val image: String,
    val title: String,
    val description: String): Serializable
