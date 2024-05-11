package com.projects.bubbles.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.projects.bubbles.components.EventCard
import com.projects.bubbles.R
import com.projects.bubbles.components.AcessCard
import com.projects.bubbles.components.PostBox
import com.projects.bubbles.dto.Post
import com.projects.bubbles.services.Service
import com.projects.bubbles.viewmodel.PostViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
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

            AcessCard()

            Spacer(modifier = Modifier.height(32.dp))

            PostsList()
        }
    }
}

@Composable
fun PostsList(viewModel: PostViewModel = PostViewModel()) {
    val posts = viewModel.posts.observeAsState().value!!
    val erro = viewModel.error.observeAsState().value!!

    if (erro.isNotBlank()) {
        Text(erro)
    }

    LazyColumn {
        item {
            // Adicione o cabeçalho aqui
            Text(text = "Cabeçalho")
        }
        items(items = posts.toList()) {
            Text("Nome: ${it.author?.username}")
        }
    }
}

@Preview
@Composable
fun PreviewFeed() {
    Feed()
}