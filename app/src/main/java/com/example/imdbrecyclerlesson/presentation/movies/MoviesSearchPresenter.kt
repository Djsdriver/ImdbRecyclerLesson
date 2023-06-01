package com.example.imdbrecyclerlesson.presentation.movies

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbrecyclerlesson.Creator
import com.example.imdbrecyclerlesson.MoviesAdapter
import com.example.imdbrecyclerlesson.R
import com.example.imdbrecyclerlesson.domain.api.MoviesInteractor
import com.example.imdbrecyclerlesson.domain.models.Movie
import com.example.imdbrecyclerlesson.domain.models.MoviesState

class MoviesSearchPresenter(
                            private val context: Context
) {

    private var view: MoviesView? = null
    private var state: MoviesState? = null
    private var latestSearchText: String? = null

    fun attachView(view: MoviesView) {
        this.view = view
        state?.let { view.render(it) }
    }

    fun detachView() {
        this.view = null
    }

    private val moviesInteractor = Creator.provideMoviesInteractor(context)

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }


    private val movies = ArrayList<Movie>()

    private val handler = Handler(Looper.getMainLooper())

    private val searchRunnable = Runnable {
        val newSearchText = lastSearchText ?: ""
        searchRequest(newSearchText)
    }


    private var lastSearchText: String? = null



    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
            return
        }

        this.latestSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(
                MoviesState(
                    movies = movies,
                    isLoading = true,
                    errorMessage = null,
                )
            )

            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>, errorMessage: String?) {
                    handler.post {
                        if (foundMovies != null) {
                            movies.clear()
                            movies.addAll(foundMovies)
                        }

                        when {
                            errorMessage != null -> {
                                renderState(
                                    MoviesState(
                                        movies = emptyList(),
                                        isLoading = false,
                                        errorMessage = context.getString(R.string.something_went_wrong),
                                    )
                                )
                                view?.showToast(errorMessage)
                            }

                            movies.isEmpty() -> {
                                renderState(
                                    MoviesState(
                                        movies = emptyList(),
                                        isLoading = false,
                                        errorMessage = context.getString(R.string.nothing_found),
                                    )
                                )
                            }

                            else -> {
                                renderState(
                                    MoviesState(
                                        movies = movies,
                                        isLoading = false,
                                        errorMessage = null,
                                    )
                                )
                            }
                        }

                    }
                }
            })
        }
    }

    private fun renderState(state: MoviesState) {
        this.state = state
        this.view?.render(state)
    }
    fun onDestroy() {
        handler.removeCallbacks(searchRunnable)
    }
}


