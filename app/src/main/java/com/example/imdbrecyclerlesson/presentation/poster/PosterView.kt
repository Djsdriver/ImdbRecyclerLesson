package com.example.imdbrecyclerlesson.presentation.poster

import com.example.imdbrecyclerlesson.domain.models.Movie

interface PosterView {

    fun setupPosterImage(url: Movie)

}