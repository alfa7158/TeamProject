package com.example.memes_app

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MemesAdapter(private val list: List<Meme>) :
    RecyclerView.Adapter<MemesAdapter.memesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemesAdapter.memesViewHolder {

        return memesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: memesViewHolder, position: Int) {

        val item = list[position]
        holder.TextToBeViewed.text = item.name
        Picasso.get().load(item.url).into(holder.imageToBeViewed)


    }

    override fun getItemCount(): Int {
        return list.size
    }


    class memesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageToBeViewed:ImageView = itemView.findViewById(R.id.memeImageView)
        val TextToBeViewed:TextView = itemView.findViewById(R.id.memeTextView)
    }

}