package com.example.imdbrecyclerlesson

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.imdbrecyclerlesson.databinding.ActivityMovieBinding

import com.example.imdbrecyclerlesson.retrofit.ImdbItemResponse
import com.google.gson.Gson


class Movie : AppCompatActivity() {

    val binding: ActivityMovieBinding by lazy {
        ActivityMovieBinding.inflate(layoutInflater)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)


        val back=findViewById<ImageButton>(R.id.back)

        val item=intent.getSerializableExtra("item") as ImdbItemResponse
        val title=findViewById<TextView>(R.id.titleDec)
        val desc=findViewById<TextView>(R.id.descriptionMovie)
        val image=findViewById<ImageView>(R.id.image)
        //val track = Gson().fromJson(intent.getStringExtra("item"), ImdbItemResponse::class.java)

        title.text=item.title
        desc.text=item.description

        Glide
            .with(image)
            .load(item.image)
            .centerCrop()
            .transform(
                RoundedCorners(
                    1
                )
            )
            .into(image)

        back.setOnClickListener {
            finish()
        }


    }
}