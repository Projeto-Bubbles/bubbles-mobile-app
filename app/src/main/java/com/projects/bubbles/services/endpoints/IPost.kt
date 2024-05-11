package com.projects.bubbles.services.endpoints

import com.projects.bubbles.dto.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface IPost {
    @GET("")
    fun getPosts(): Call<List<Post>>

    @GET("{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>

    @POST("")
    fun createPost(@Body newPost: Post): Call<Post>

    @PUT("{id}")
    fun updatePost(@Path("id") id: Int, @Body editedPost: Post): Call<Post>

    @DELETE("{id}")
    fun deletePost(@Path("id") id: Int): Call<Void>
}