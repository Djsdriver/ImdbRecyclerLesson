package com.example.imdbrecyclerlesson.domain.impl

import com.example.imdbrecyclerlesson.Resource
import com.example.imdbrecyclerlesson.data.NetworkClient
import com.example.imdbrecyclerlesson.data.dto.MoviesSearchRequest
import com.example.imdbrecyclerlesson.data.dto.MoviesSearchResponse
import com.example.imdbrecyclerlesson.domain.api.MoviesRepository
import com.example.imdbrecyclerlesson.domain.models.Movie

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description)})
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}