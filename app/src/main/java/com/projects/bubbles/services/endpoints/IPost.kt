package com.projects.bubbles.services.endpoints

import com.projects.bubbles.dto.Post
import com.projects.bubbles.dto.PostRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface IPost {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>

    @POST("posts")
    suspend fun createPost(@Body newPost: PostRequest): Response<Post>

    @PATCH("posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body editedPost: Post): Response<Post>

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int): Response<Void>
}