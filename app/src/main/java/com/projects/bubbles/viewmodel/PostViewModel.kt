package com.projects.bubbles.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projects.bubbles.dto.Post
import com.projects.bubbles.dto.PostRequest
import com.projects.bubbles.services.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
        CoroutineScope(Dispatchers.IO).launch {
            try {
                loading.postValue(true)
                val response = postService.getPosts()
                Log.d("Resposta da API", response.body().toString())

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        posts.postValue(response.body()?.toMutableList() ?: mutableListOf())
                        erro.postValue("")
                    } else {
                        erro.postValue(response.errorBody()?.string())
                        Log.e("api", "Não deu sucesso! ${erro.value}")
                    }
                    loading.value = false
                }
            } catch (e: Exception) {
                Log.e("api", "Deu ruim rapazz no get! ${e.message}")
                withContext(Dispatchers.Main) {
                    erro.value = e.message
                    loading.value = false
                }
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

    fun deletePost(postId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                loading.postValue(true)
                val response = postService.deletePost(postId)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        getPosts()
                        Log.d("PostViewModel", "Post deleted successfully")
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.e("PostViewModel", "Error deleting post: $errorBody")
                        erro.postValue("Erro ao excluir post: $errorBody")
                    }
                }
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error deleting post: ${e.message}")
                erro.postValue("Erro ao excluir post: ${e.message}")
            } finally {
                delay(5000)
                loading.postValue(false)
            }
        }
    }

    fun updatePost(postId: Int, newContent: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                loading.value = true
                val updatedPost = Post(idPost = postId, contents = newContent)
                val response = postService.updatePost(postId, updatedPost)
                if (response.isExecuted) {
                    getPosts()
                } else {
                    Log.e("PostViewModel", "Error updating post: ${response}")
                    erro.postValue("Erro ao atualizar post: ${response}")
                }
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error updating post: ${e.message}")
                erro.postValue("Erro ao atualizar post: ${e.message}")
            } finally {
                loading.value = false
            }
        }
    }

}
