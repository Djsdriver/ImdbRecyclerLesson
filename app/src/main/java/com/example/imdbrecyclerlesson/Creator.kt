package com.example.imdbrecyclerlesson

import android.app.Activity
import android.content.Context
import com.example.imdbrecyclerlesson.domain.impl.MoviesRepositoryImpl
import com.example.imdbrecyclerlesson.data.network.RetrofitNetworkClient
import com.example.imdbrecyclerlesson.domain.api.MoviesInteractor
import com.example.imdbrecyclerlesson.domain.api.MoviesRepository
import com.example.imdbrecyclerlesson.domain.impl.MoviesInteractorImpl
import com.example.imdbrecyclerlesson.presentation.MoviesSearchController
import com.example.imdbrecyclerlesson.presentation.PosterController

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchController(activity: Activity, adapter: MoviesAdapter): MoviesSearchController {
        return MoviesSearchController(activity, adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }


}