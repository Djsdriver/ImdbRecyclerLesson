package com.example.imdbrecyclerlesson

import android.app.Activity
import android.content.Context
import com.example.imdbrecyclerlesson.domain.impl.MoviesRepositoryImpl
import com.example.imdbrecyclerlesson.data.network.RetrofitNetworkClient
import com.example.imdbrecyclerlesson.domain.api.MoviesInteractor
import com.example.imdbrecyclerlesson.domain.api.MoviesRepository
import com.example.imdbrecyclerlesson.domain.impl.MoviesInteractorImpl
import com.example.imdbrecyclerlesson.presentation.movies.MoviesSearchPresenter
import com.example.imdbrecyclerlesson.presentation.PosterController
import com.example.imdbrecyclerlesson.presentation.movies.MoviesView

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    /*fun provideMoviesSearchPresenter(activity: Activity, adapter: MoviesAdapter): MoviesSearchPresenter {
        return MoviesSearchPresenter(activity, adapter)
    }*/
    fun provideMoviesSearchPresenter(moviesView: MoviesView, context: Context, adapter: MoviesAdapter): MoviesSearchPresenter {
        return MoviesSearchPresenter(view = moviesView, context = context, adapter = adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }


}