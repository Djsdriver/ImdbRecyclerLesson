package com.example.imdbrecyclerlesson.domain.api

import com.example.imdbrecyclerlesson.Resource
import com.example.imdbrecyclerlesson.domain.models.Movie

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
}