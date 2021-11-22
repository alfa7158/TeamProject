package com.example.facebookapp_warm_up

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class RecyclerViewCommintsAdapter(private val list: List<postCommentModel>) :
    RecyclerView.Adapter<RecyclerViewCommintsAdapter.CommitsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewCommintsAdapter.CommitsViewHolder {

        return CommitsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_recycler_view_seond,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommitsViewHolder, position: Int) {

        val item = list[position]
        holder.commentsBodyTextView.text = item.body
        holder.commentsNameTextView.text = item.name
        holder.commentsIDTextView.text = item.id.toString()
        holder.commentsEmailTextView.text = item.email
        holder.commentsPostIDTextView.text = item.postId.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class CommitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commentsIDTextView: TextView = itemView.findViewById(R.id.commentID)
        val commentsBodyTextView: TextView = itemView.findViewById(R.id.commentBody)
        val commentsEmailTextView: TextView = itemView.findViewById(R.id.commentEmail)
        val commentsNameTextView: TextView = itemView.findViewById(R.id.commentName)
        val commentsPostIDTextView: TextView = itemView.findViewById(R.id.commentPostID)
    }

}