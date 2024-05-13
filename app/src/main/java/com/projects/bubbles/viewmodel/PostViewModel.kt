package com.projects.bubbles.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projects.bubbles.dto.Post
import com.projects.bubbles.dto.PostRequest
import com.projects.bubbles.services.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    val posts = MutableLiveData<MutableList<Post>>()
    val erro = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    private val postService = Service.PostService()

    init {
        getPosts()
    }

    private fun getPosts() {
        loading.value = true // Define o estado de loading como verdadeiro ao iniciar o carregamento
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = postService.getPosts()
                Log.d("Resposta da API", response.body().toString())

                if (response.isSuccessful) {
                    val postList = response.body()?.toMutableList() ?: mutableListOf()
                    posts.postValue(postList)
                    erro.postValue("")
                } else {
                    erro.postValue(response.errorBody()?.string())
                    Log.e("api", "Não deu sucesso! ${erro.value}")
                }
            } catch (e: Exception) {
                Log.e("api", "Deu ruim rapazz no get! ${e.message}")
                erro.postValue(e.message)
            } finally {
                loading.postValue(false) // Define o estado de loading como falso ao finalizar o carregamento
            }
        }
    }

    fun createPost(post: PostRequest) {
        Log.d("api", "ESTOU QUASE FAZENDO O POST")

        loading.value = true // Define o estado de loading como verdadeiro ao iniciar a criação do post
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = postService.createPost(post)
                if (response.isSuccessful) {
                    // Atualiza a lista de posts após a criação bem-sucedida
                    getPosts()
                } else {
                    erro.postValue(response.errorBody()?.string())
                    Log.e("api", "Não deu sucesso ao criar o post! ${erro.value}")
                }
            } catch (e: Exception) {
                Log.e("api", "Deu ruim rapazz ao criar o post! ${e.message}")
                erro.postValue(e.message)
            } finally {
                loading.postValue(false) // Define o estado de loading como falso ao finalizar a criação do post
            }
        }
    }
}
