package com.projects.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projects.bubbles.R
import com.projects.bubbles.components.AcessCard
import com.projects.bubbles.components.EventStoryCard

@Composable
fun Feed() {
    // val posts = remember { mutableStateListOf<String>() }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(start = 28.dp, end = 28.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                EventStoryCard(image = painterResource(id = R.mipmap.event_bg))
                EventStoryCard(image = painterResource(id = R.mipmap.event_bg_2))
                EventStoryCard(image = painterResource(id = R.mipmap.event_bg_3))
                EventStoryCard(image = painterResource(id = R.mipmap.event_bg_4))
            }

            Spacer(modifier = Modifier.height(16.dp))

            AcessCard()

            Spacer(modifier = Modifier.height(32.dp))

//            PostsList()
        }
    }
}

//@Composable
//fun PostsList(viewModel: PostViewModel = PostViewModel()) {
//    val posts = viewModel.posts.observeAsState().value!!
//    val erro = viewModel.error.observeAsState().value!!
//
//    item {
//        // Adicione o cabeçalho aqui
//        Text(text = "Cabeçalho")
//    }
//}
//
//
//if (erro.isNotBlank()) {
//    Text(erro)
//}

@Preview
@Composable
fun PreviewFeed() {
    Feed()
}