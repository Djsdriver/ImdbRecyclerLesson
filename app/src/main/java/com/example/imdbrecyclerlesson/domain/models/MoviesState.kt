package com.example.imdbrecyclerlesson.domain.models

data class MoviesState(
    val movies: List<Movie>,
    val isLoading: Boolean,
    val errorMessage: String?
)