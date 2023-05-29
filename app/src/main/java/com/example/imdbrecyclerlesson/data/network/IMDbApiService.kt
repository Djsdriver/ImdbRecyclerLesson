package com.example.imdbrecyclerlesson.data.network

import com.example.imdbrecyclerlesson.data.dto.MoviesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {

    @GET("/en/API/SearchMovie/k_43062fj1/{expression}")
    fun getImdbExpression(@Path("expression") expression: String): Call<MoviesSearchResponse>
}