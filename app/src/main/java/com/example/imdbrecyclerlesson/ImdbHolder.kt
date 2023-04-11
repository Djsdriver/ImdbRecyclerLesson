package com.example.imdbrecyclerlesson

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imdbrecyclerlesson.retrofit.ImdbItemResponse

class ImdbHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val title=itemView.findViewById<TextView>(R.id.titleDec)
    val image=itemView.findViewById<ImageView>(R.id.imageView)
    val description=itemView.findViewById<TextView>(R.id.descriptionMovie)


    fun bind(item: ImdbItemResponse, onClickMovie: onClickMovie){
        title.text=item.title
        description.text=item.description
        Glide.with(itemView).load(item.image).into(image)
        itemView.setOnClickListener {
            onClickMovie.onClick(item)
        }
    }

}