package com.example.imdbrecyclerlesson.ui.poster

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.imdbrecyclerlesson.Creator
import com.example.imdbrecyclerlesson.R
import com.example.imdbrecyclerlesson.databinding.ActivityMovieBinding

import com.example.imdbrecyclerlesson.domain.models.Movie
import kotlin.system.measureTimeMillis


class Poster : AppCompatActivity() {

    val binding: ActivityMovieBinding by lazy {
        ActivityMovieBinding.inflate(layoutInflater)
    }
    private val posterController = Creator.providePosterController(this)


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        posterController.onCreate()

    }
}