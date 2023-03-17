package com.example.imdbrecyclerlesson.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ImdbApi {

    @GET("/en/API/SearchMovie/k_43062fj1/{expression}")
    fun getImdbExpression(@Path("expression") expression: String): Call<ImdbItemsResult>
}