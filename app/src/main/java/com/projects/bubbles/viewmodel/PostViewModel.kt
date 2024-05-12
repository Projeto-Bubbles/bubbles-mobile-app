package com.projects.bubbles.viewmodel

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projects.bubbles.dto.Post
import com.projects.bubbles.services.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    val posts = MutableLiveData<MutableList<Post>>()

    val erro = MutableLiveData("")

    private val postService = Service.PostService()

    init {
        getPosts()
    }

    private fun getPosts() {
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
            }
        }
    }
}