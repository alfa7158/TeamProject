package com.example.facebookapp_warm_up

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RecyclerViewSeond : Fragment() {
    private val comments = mutableListOf<postCommentModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view_seond, container, false)
    }

    override fun onViewCreated(commentsView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(commentsView, savedInstanceState)

        val commentRecyclerView: RecyclerView = commentsView.findViewById(R.id.comment_layout)
        val commentAapter = RecyclerViewCommintsAdapter(comments)
        commentRecyclerView.adapter = commentAapter
        val retrofit_ = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com").addConverterFactory(
            GsonConverterFactory.create()).build()
        val postsAPI_ = retrofit_.create(PostCommintsAPI::class.java)
        postsAPI_.getListComments(1).enqueue(object: Callback<List<postCommentModel>> {
            override fun onResponse(
                call: Call<List<postCommentModel>>,
                response: Response<List<postCommentModel>>
            ) {
                response.body()?.run {
                    Log.d("POSTS","$this")
                    comments.addAll(this)
                    commentAapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<List<postCommentModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })


    }


}