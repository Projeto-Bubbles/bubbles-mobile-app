package com.projects.bubbles.services

import com.projects.bubbles.services.endpoints.IAuth
import com.projects.bubbles.services.endpoints.IBubble
import com.projects.bubbles.services.endpoints.IPost
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Service {

    private const val BASE_URL = "http://10.0.2.2:8080/api/"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
    }

    val PostService: IPost by lazy { retrofitBuilder.build().create(IPost::class.java) }
    val AuthService: IAuth by lazy { retrofitBuilder.build().create(IAuth::class.java) }
    val BubbleService: IBubble by lazy { retrofitBuilder.build().create(IBubble::class.java) }
}
