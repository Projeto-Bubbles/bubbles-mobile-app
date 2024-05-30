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
import kotlinx.coroutines.withContext

class PostViewModel : ViewModel() {
    val posts = MutableLiveData<MutableList<Post>>()
    val erro = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val postCreated = MutableLiveData<Boolean>()


    private val postService = Service.PostService()

    init {
        getPosts()
    }

    fun getPosts() {
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

        loading.value =
            true // Define o estado de loading como verdadeiro ao iniciar a criação do post
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = postService.createPost(post)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        getPosts()
                        postCreated.postValue(true) // Sinaliza que um novo post foi criado
                    } else {
                        erro.postValue(response.errorBody()?.string())
                        Log.e("api", "Não deu sucesso ao criar o post! ${response}")
                    }
                    loading.postValue(false) // Define o estado de loading como falso ao finalizar a criação do post
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("api", "Deu ruim rapazz ao criar o post! ${e.message}")
                    erro.postValue(e.message)
                    loading.postValue(false) // Define o estado de loading como falso ao finalizar a criação do post
                }
            }
        }
    }

    fun deletePostById(postId: Int) {
        loading.value = true // Define o estado de loading como verdadeiro ao iniciar a exclusão do post
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = postService.deletePost(postId)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        // Aqui você pode implementar qualquer ação após a exclusão bem-sucedida, como atualizar a lista de posts
                        // No exemplo abaixo, estou apenas imprimindo uma mensagem de log
                        Log.d("api", "Post excluído com sucesso!")
                    } else {
                        erro.postValue(response.errorBody()?.string())
                        Log.e("api", "Não foi possível excluir o post! ${erro.value}")
                    }
                    loading.postValue(false) // Define o estado de loading como falso ao finalizar a exclusão do post
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("api", "Ocorreu um erro ao excluir o post! ${e.message}")
                    erro.postValue(e.message)
                    loading.postValue(false) // Define o estado de loading como falso ao finalizar a exclusão do post
                }
            }
        }
    }



}
