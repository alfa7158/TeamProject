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



class RecyclerViewFirst : Fragment() {
    private val posts = mutableListOf<PostsModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val psotRecyclerView: RecyclerView = view.findViewById(R.id.PostRecyclerView_layout)
        val postAapter = RecyclerViewPostsAdapter(posts)
        psotRecyclerView.adapter = postAapter
        val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com").addConverterFactory(
            GsonConverterFactory.create()).build()
        val postsAPI = retrofit.create(PostCommintsAPI::class.java)
        postsAPI.getListPosts().enqueue(object: Callback<List<PostsModel>> {
            override fun onResponse(
                call: Call<List<PostsModel>>,
                response: Response<List<PostsModel>>
            ) {
                response.body()?.run {
                    Log.d("POSTS","$this")
                    posts.addAll(this)
                    postAapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<List<PostsModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }


}