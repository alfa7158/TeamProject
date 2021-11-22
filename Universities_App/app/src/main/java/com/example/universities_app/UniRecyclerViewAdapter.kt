package com.example.universities_app

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

class UniRecyclerViewAdapter(private val list: List<uniModel>) :
    RecyclerView.Adapter<UniRecyclerViewAdapter.uniViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UniRecyclerViewAdapter.uniViewHolder {

        return uniViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: uniViewHolder, position: Int) {

        val item = list[position]
        holder.textToBeViewed.text = item.name
//        holder.textToSearchedFor.setText(item.country)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class uniViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textToBeViewed:TextView = itemView.findViewById(R.id.uniTextView)
//        val textToSearchedFor:EditText = itemView.findViewById(R.id.uniEditText)



    }

}