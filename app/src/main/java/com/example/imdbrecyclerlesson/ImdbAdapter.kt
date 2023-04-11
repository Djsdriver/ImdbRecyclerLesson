package com.example.imdbrecyclerlesson

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbrecyclerlesson.retrofit.ImdbItemResponse

class ImdbAdapter(private val list: List<ImdbItemResponse>,  val onClickMovie: onClickMovie): RecyclerView.Adapter<ImdbHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImdbHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ImdbHolder(view)
    }

    override fun onBindViewHolder(holder: ImdbHolder, position: Int) {
        holder.bind(list[position],onClickMovie)
    }

    override fun getItemCount(): Int =list.size
    }

interface onClickMovie{

    fun onClick(item: ImdbItemResponse)
}

