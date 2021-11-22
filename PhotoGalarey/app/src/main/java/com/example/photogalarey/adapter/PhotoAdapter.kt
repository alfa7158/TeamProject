package com.example.photogalarey.adapter

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.photogalarey.R
import com.example.photogalarey.ThumbnailDownloader
import com.example.photogalarey.model.GalleryItem

class PhotoAdapter(private val list: List<GalleryItem>,
val thumbnailDownloader: ThumbnailDownloader<PhotoHolder>) :
    RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.PhotoHolder {

        return PhotoHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false

        )as ImageView)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {

        val item = list[position]

        val placeholder:Drawable = ContextCompat.getDrawable(
            holder.itemView.context,R.drawable.ic_launcher_foreground
        )?:ColorDrawable()

        holder.bindDrawable(placeholder)
        thumbnailDownloader.queueThumnail(holder,item.url)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class PhotoHolder(itemView: ImageView) : RecyclerView.ViewHolder(itemView) {
        val bindDrawable: (Drawable) -> Unit = itemView::setImageDrawable

    }

}