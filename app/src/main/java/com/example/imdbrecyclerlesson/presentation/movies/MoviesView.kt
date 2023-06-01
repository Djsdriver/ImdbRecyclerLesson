package com.example.imdbrecyclerlesson.presentation.movies

import com.example.imdbrecyclerlesson.domain.models.Movie
import com.example.imdbrecyclerlesson.domain.models.MoviesState
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface MoviesView: MvpView{

    @AddToEndSingle
    fun render(state: MoviesState)

    @OneExecution
    fun showToast(additionalMessage: String)

}