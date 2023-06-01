package com.example.imdbrecyclerlesson

import android.app.Application
import com.example.imdbrecyclerlesson.presentation.movies.MoviesSearchPresenter

class MoviesApplication : Application() {

    var moviesSearchPresenter : MoviesSearchPresenter? = null

}