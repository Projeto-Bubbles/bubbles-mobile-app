package com.projects.bubbles.services

import com.projects.bubbles.services.endpoints.IPost
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Service {
    val BASE_URL = "http://localhost:8080/"

    fun PostService(): IPost {
        val post = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IPost::class.java)
        return post
    }
}