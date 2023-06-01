package com.example.imdbrecyclerlesson.presentation.movies

import com.example.imdbrecyclerlesson.domain.models.Movie
import com.example.imdbrecyclerlesson.domain.models.MoviesState

interface MoviesView {

   /* fun showPlaceholderMessage(isVisible: Boolean)

    fun showMoviesList(isVisible: Boolean)

    fun showProgressBar(isVisible: Boolean)

    fun changePlaceholderText(newPlaceholderText: String)

    fun updateMoviesList(newMoviesList: List<Movie>)*/

    // Состояние «загрузки»
    fun showLoading()

    // Состояние «ошибки»
    fun showError(errorMessage: String)

    // Состояние «пустого списка»
    fun showEmpty(emptyMessage: String)

    // Состояние «контента»
    fun showContent(movies: List<Movie>)

    fun render(state: MoviesState)

    fun showToast(message: String)

}