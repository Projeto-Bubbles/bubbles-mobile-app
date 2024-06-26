package com.projects.bubbles.screens

import AuthViewModel
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.projects.bubbles.R
import com.projects.bubbles.components.CreatePostBox
import com.projects.bubbles.components.EventStoryCard
import com.projects.bubbles.components.PostBox
import com.projects.bubbles.components.PostBoxSkeleton
import com.projects.bubbles.dto.User
import com.projects.bubbles.utils.Loading
import com.projects.bubbles.utils.ShimmerEffect
import com.projects.bubbles.viewmodel.PostViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Feed(
    postViewModel: PostViewModel,
    authViewModel: AuthViewModel = viewModel(),
    context: Context
) {
    val userState = authViewModel.userState.collectAsState().value

    LaunchedEffect(Unit) {
        authViewModel.loadUserFromDataStore(context)
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        ) {

            Spacer(modifier = Modifier.height(70.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                EventStoryCard(image = painterResource(id = R.mipmap.event_bg))
                EventStoryCard(image = painterResource(id = R.mipmap.event_bg_2))
                EventStoryCard(image = painterResource(id = R.mipmap.event_bg_3))
                EventStoryCard(image = painterResource(id = R.mipmap.event_bg_4))
            }

            Spacer(modifier = Modifier.height(20.dp))

            userState?.let { user ->
                CreatePostBox(
                    user = userState,
                    postViewModel = postViewModel
                )

                Spacer(modifier = Modifier.height(20.dp))

                PostList(postViewModel, User(user.idUser, user.username, user.nickname, user.email))
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostList(viewModel: PostViewModel, userState: User) {
    val posts = viewModel.posts.observeAsState().value
    val erro = viewModel.erro.observeAsState().value
    val isLoading = viewModel.isLoading.observeAsState().value
    val postCreated = viewModel.postCreated.observeAsState().value


    if (!erro.isNullOrEmpty()) {
        Text(erro)
    }

    if (postCreated == true) {
        viewModel.postCreated.value = false
        viewModel.getPosts()
    }

    if (isLoading == true) {
        LazyColumn() {
            items(5) {
                PostBoxSkeleton()

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    } else {
        posts?.let { postList ->
            LazyColumn {
                items(items = postList) { post ->
                    PostBox(
                        username = post.author?.username!!,
                        nickname = post.author?.nickname!!,
                        dateTime = post.moment,
                        content = post.contents,
                        viewModel,
                        post = post,
                        onEditClick = { updatedPost ->
                            viewModel.updatePost(updatedPost.idPost!!, updatedPost.contents)
                        },
                        userState = userState
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }

}


