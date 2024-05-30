package com.projects.bubbles.services

import com.projects.bubbles.services.endpoints.IAuth
import com.projects.bubbles.services.endpoints.IPost
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Service {
//    const val BASE_URL = "http://34.195.120.16/api/"
    const val BASE_URL = "http://10.0.2.2:8080/api/"

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS) // Configura o tempo de espera de conexão
        .readTimeout(15, TimeUnit.SECONDS) // Configura o tempo de espera de leitura
        .writeTimeout(15, TimeUnit.SECONDS) // Configura o tempo de espera de escrita
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

    fun AuthService(): IAuth {
        val auth = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient) // Define o cliente OkHttpClient com as configurações de tempo de espera
            .build()
            .create(IAuth::class.java)

        return auth
    }

}