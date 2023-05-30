package com.example.imdbrecyclerlesson.ui.poster

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.imdbrecyclerlesson.Creator
import com.example.imdbrecyclerlesson.R

import com.example.imdbrecyclerlesson.domain.models.Movie
import com.example.imdbrecyclerlesson.presentation.PosterPresenter
import com.example.imdbrecyclerlesson.presentation.poster.PosterView


class Poster : AppCompatActivity(), PosterView {
    private lateinit var posterPresenter: PosterPresenter
    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var desc: TextView
    private lateinit var back: ImageButton



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        val item=intent.getSerializableExtra("poster") as Movie
        image=findViewById(R.id.image)
        back=findViewById(R.id.back)
        title=findViewById(R.id.titleDec)
        desc=findViewById(R.id.descriptionMovie)

        posterPresenter = Creator.providePosterPresenter(this, item)
        title.text = item.title
        desc.text = item.description

        posterPresenter.onCreate()

        back.setOnClickListener {
            finish()
        }

    }

    override fun setupPosterImage(url: Movie) {
        Glide.with(applicationContext)
            .load(url.image)
            .into(image)
    }

}