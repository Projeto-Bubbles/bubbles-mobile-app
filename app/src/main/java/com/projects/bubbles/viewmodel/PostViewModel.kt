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
    val posts = MutableLiveData(SnapshotStateList<Post>())

    val error = MutableLiveData("")

    private val postService = Service.PostService()

    init {
        getPosts()
    }

    private fun getPosts() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = postService.getPosts()

                if (response.isSuccessful) {
                    posts.value!!.clear()
                    posts.value!!.addAll(response.body() ?: emptyList())
                    error("")
                } else {
                    Log.e("API", "Erro ${response.code()}: ${response.message()}")
                    error.postValue(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                Log.e("api", "Deu ruim rapazz no get! ${e.message}")
                error.postValue(e.message)
            }
        }
    }

//    public fun criar(novoFilme: Filme){
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val post = apiposts.post(novoFilme)
//
//                if (post.isSuccessful) {
//                    getposts()
//                    error.postValue("")
//                } else {
//                    error.postValue(post.errorrBody()?.string())
//                }
//            } catch (e: Exception) {
//                Log.e("api", "Deu ruim rapazz no create! ${e.message}")
//                error.postValue(e.message)
//            }
//        }
//
//    }
}