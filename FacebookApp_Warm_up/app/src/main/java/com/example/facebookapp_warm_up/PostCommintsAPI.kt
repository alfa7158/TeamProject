package com.example.facebookapp_warm_up

import retrofit2.http.GET
import retrofit2.http.Query
//https://jsonplaceholder.typicode.com/posts


///commints : https://jsonplaceholder.typicode.com/comments?postId=1
interface PostCommintsAPI {

    @GET("/posts")
    fun getListPosts(
    ): retrofit2.Call<List<PostsModel>>

    @GET("/comments")
    fun getListComments( @Query("postId")postId:Int):retrofit2.Call<List<postCommentModel>>


}