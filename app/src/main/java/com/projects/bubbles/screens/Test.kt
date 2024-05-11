package com.projects.bubbles.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projects.bubbles.R
import com.projects.bubbles.dto.Post
import com.projects.bubbles.services.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.foundation.lazy.items
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Test() {
    val context = LocalContext.current

    val backgroundImage: Painter = painterResource(id = R.drawable.selection_bubbles)

    val posts = remember { mutableStateListOf<Post>() }
    val error = remember { mutableStateOf("") }

    val (post, postSetter) = remember { mutableStateOf(Post()) }

    Surface{
        Column {
            if (error.value.isNotBlank()) {
                Text(error.value, color = Color.Red)
            }

            if (posts.isEmpty()) {
                TextButton(onClick = {
                    updatePost(posts, error)
                }) {
                    Text("Sem Posts - Clique para atualizar")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .height(200.dp)
                ) {
                    items(items = posts.toList()) {
                        Text("idPost: ${it.idPost}. Momento: ${it.moment}. Conteúdo: ${it.contents} etc...")
                    }
                }
            }

            Text("Novo Post")

            OutlinedTextField(value = post.idPost?.toString()?: "",
                onValueChange = {
                    postSetter(post.copy(idPost = it.toInt()))
                }, label = { Text("Id do Post") }
            )

            OutlinedTextField(value = post.moment?.toString() ?: "",
                onValueChange = {
                    postSetter(post.copy(moment = LocalDateTime.now()))
                }, label = { Text("DataHora do Post") }
            )

            OutlinedTextField(value = post.contents?: "",
                onValueChange = {
                    postSetter(post.copy(contents = it))
                }, label = { Text("DataHora do Post") }
            )

            Button(onClick = {
                createPost(post, posts, error)
            }) {
                Text("Cadastrar Post")
            }
        }
    }
}

fun createPost(Post: Post, Posts: SnapshotStateList<Post>, error: MutableState<String>) {
    val apiPosts = Service.PostService()
    val post = apiPosts.createPost(Post)

    post.enqueue(object : Callback<Post> {
        override fun onResponse(call: Call<Post>, response: Response<Post>) {
            if (response.isSuccessful) {
                updatePost(Posts, error)
                error.value = ""
            } else {
                error.value = response.errorBody()!!.string()
            }
        }

        override fun onFailure(call: Call<Post>, t: Throwable) {
            TODO("Not yet implemented")
        }
    })
}

fun updatePost(Posts: MutableList<Post>, error: MutableState<String>) {
    val apiPosts = Service.PostService()
    val get = apiPosts.getPosts()

    get.enqueue(object : Callback<List<Post>> {
        override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    Posts.clear()
                    Posts.addAll(responseBody)
                }
                error.value = ""
            } else {
                error.value = response.errorBody()!!.string()
            }
        }

        override fun onFailure(call: Call<List<Post>>, t: Throwable) {
            TODO("Not yet implemented")
        }
    })
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewTest() {
    SelectCategory()
}