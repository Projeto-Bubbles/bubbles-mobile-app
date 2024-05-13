package com.projects.bubbles.services

import com.projects.bubbles.services.endpoints.IPost
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Service {
    const val BASE_URL = "http://10.18.32.92/"

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(12, TimeUnit.SECONDS) // Configura o tempo de espera de conexão
        .readTimeout(12, TimeUnit.SECONDS) // Configura o tempo de espera de leitura
        .writeTimeout(12, TimeUnit.SECONDS) // Configura o tempo de espera de escrita
        .build()

    fun PostService(): IPost {
        val post = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient) // Define o cliente OkHttpClient com as configurações de tempo de espera
            .build()
            .create(IPost::class.java)

        return post
    }

}