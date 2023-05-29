package com.example.imdbrecyclerlesson.data

import com.example.imdbrecyclerlesson.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response

}