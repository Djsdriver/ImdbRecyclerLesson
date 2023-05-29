package com.example.imdbrecyclerlesson.data.dto

import com.example.imdbrecyclerlesson.domain.models.Movie

data class MoviesSearchResponse(
                           val results: List<MovieDto>): Response()
