package com.projects.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projects.bubbles.components.EventCard
import com.projects.bubbles.R
import com.projects.bubbles.components.AcessCard

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
                EventCard(image = painterResource(id = R.mipmap.event_bg))
                EventCard(image = painterResource(id = R.mipmap.event_bg_2))
                EventCard(image = painterResource(id = R.mipmap.event_bg_3))
                EventCard(image = painterResource(id = R.mipmap.event_bg_4))
            }

            Spacer(modifier = Modifier.height(16.dp))

            AcessCard()

            Spacer(modifier = Modifier.height(32.dp))

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                /* items(posts) { post ->
                     PostBox(
                        userName = post.userName,
                        userUsername = post.userUsername,
                        postTime = post.postTime,
                        commentContent = post.commentContent
                    )
               } */
            }
        }
    }
}

@Preview
@Composable
fun PreviewFeed() {
    Feed()
}