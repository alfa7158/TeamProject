package com.example.facebookapp_warm_up

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController

class RecyclerViewPostsAdapter(private val list: MutableList<PostsModel>) :
    RecyclerView.Adapter<RecyclerViewPostsAdapter.PostsRecyclerViewCommints>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewPostsAdapter.PostsRecyclerViewCommints {

        return PostsRecyclerViewCommints(
            LayoutInflater.from(parent.context).inflate(
                R.layout.post_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostsRecyclerViewCommints, position: Int) {

        val item = list[position]
        holder.PostTitleTextView.text = item.body
        holder.PostIDTextView.text = item.id.toString()
        holder.PostBodyTextView.text = item.body
        holder.PostTitleTextView.setOnClickListener {
            it.findNavController().navigate(R.id.action_recyclerViewFirst_to_recyclerViewSeond)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }


    class PostsRecyclerViewCommints(postView: View) : RecyclerView.ViewHolder(postView) {
        val PostIDTextView:TextView = itemView.findViewById(R.id.postID)
        val PostBodyTextView:TextView = itemView.findViewById(R.id.postBody)
        val PostTitleTextView:TextView = itemView.findViewById(R.id.postTitle)
        val PostUserIDTextView:TextView = itemView.findViewById(R.id.postUserID)
    }

}