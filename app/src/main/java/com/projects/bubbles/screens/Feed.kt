package com.projects.bubbles.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projects.bubbles.components.EventCard
import com.projects.bubbles.R
import com.projects.bubbles.components.AccessCard
import com.projects.bubbles.components.CreatePostBox
import com.projects.bubbles.components.PostBox
import com.projects.bubbles.viewmodel.PostViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Feed() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        ) {
            Spacer(modifier = Modifier.height(70.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                EventCard(image = painterResource(id = R.mipmap.event_bg))
                EventCard(image = painterResource(id = R.mipmap.event_bg_2))
                EventCard(image = painterResource(id = R.mipmap.event_bg_3))
                EventCard(image = painterResource(id = R.mipmap.event_bg_4))
            }

            Spacer(modifier = Modifier.height(16.dp))

//            AccessCard()

            CreatePostBox(
                username = "Ruan",
                nickname = "helloWorldRuan",
            )

            Spacer(modifier = Modifier.height(32.dp))

            PostList()
        }
    }
}

@Composable
fun PostList(viewModel: PostViewModel = PostViewModel()) {
    val posts = viewModel.posts.observeAsState().value
    val erro = viewModel.erro.observeAsState().value
    val loading = viewModel.loading.observeAsState().value

    if (!erro.isNullOrEmpty()) {
        Text(erro)
    }

    // Exibe o indicador de carregamento se o estado de loading for verdadeiro
    if (loading == true) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.Blue, modifier = Modifier.width(64.dp))
        }
    }

    posts?.let { postList ->
        LazyColumn {
            items(items = postList) { post ->
                PostBox(
                    username = post.author.username,
                    nickname = post.author.nickname,
                    dateTime = post.moment,
                    content = post.contents,
                    commentContent = post.comments
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }


    }
}

@Preview
@Composable
fun PreviewFeed() {
    Feed()
}