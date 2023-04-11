package com.example.imdbrecyclerlesson.retrofit

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class ImdbItemResponse(
    val id: String,
    val resultType: String,
    val image: String,
    val title: String,
    val description: String): Serializable
