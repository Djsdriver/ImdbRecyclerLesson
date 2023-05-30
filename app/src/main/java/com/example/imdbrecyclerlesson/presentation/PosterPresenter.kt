package com.example.imdbrecyclerlesson.presentation

import android.annotation.SuppressLint
import com.example.imdbrecyclerlesson.domain.models.Movie
import com.example.imdbrecyclerlesson.presentation.poster.PosterView

class PosterPresenter(private val view: PosterView,
                      private val item: Movie,) {



    @SuppressLint("MissingInflatedId")
    fun onCreate() {
        view.setupPosterImage(item)

        /*val back=activity.findViewById<ImageButton>(R.id.back)

        //
        //Log.d("MyLog", "$item")
        val title=activity.findViewById<TextView>(R.id.titleDec)
        val desc=activity.findViewById<TextView>(R.id.descriptionMovie)
        val image=activity.findViewById<ImageView>(R.id.image)*/

        /* метод через перевода класса в json формат
         val elapsedTime = measureTimeMillis {
             val track = Gson().fromJson(intent.getStringExtra("item"), ImdbItemResponse::class.java)
             Log.d("MyLog", "$track")
             title.text=track.title
             desc.text=track.description

             Glide
                 .with(image)
                 .load(track.image)
                 .centerCrop()
                 .transform(
                     RoundedCorners(
                         1
                     )
                 )
                 .into(image)
         }
         Log.d("MyLog", "$elapsedTime ms")*/


        /*//метод через перевода класса через сериализацию
        val elapsedTime = measureTimeMillis {
            val item=activity.intent.getSerializableExtra("poster") as Movie
            title.text = item.title
            desc.text = item.description

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
        }
        Log.d("MyLog", "$elapsedTime ms")*/




    }
}