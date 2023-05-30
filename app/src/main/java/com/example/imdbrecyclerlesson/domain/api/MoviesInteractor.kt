package com.example.imdbrecyclerlesson.domain.api

import com.example.imdbrecyclerlesson.domain.models.Movie

interface MoviesInteractor {
    fun searchMovies(expression: String, consumer: MoviesConsumer)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>, errorMessage: String?)
    }
}