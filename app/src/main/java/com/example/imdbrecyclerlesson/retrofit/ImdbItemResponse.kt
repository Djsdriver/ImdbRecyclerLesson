package com.example.imdbrecyclerlesson.retrofit

data class ImdbItemResponse(val id: String,
                            val resultType: String,
                            val image: String,
                            val title: String,
                            val description: String)
