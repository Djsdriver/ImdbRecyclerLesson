package com.example.imdbrecyclerlesson

import com.example.imdbrecyclerlesson.data.network.MoviesRepositoryImpl
import com.example.imdbrecyclerlesson.data.network.RetrofitNetworkClient
import com.example.imdbrecyclerlesson.domain.api.MoviesInteractor
import com.example.imdbrecyclerlesson.domain.api.MoviesRepository
import com.example.imdbrecyclerlesson.domain.impl.MoviesInteractorImpl

object Creator {
    private fun getMoviesRepository(): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideMoviesInteractor(): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository())
    }
}