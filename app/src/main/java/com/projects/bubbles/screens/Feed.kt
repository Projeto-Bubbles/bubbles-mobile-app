package com.projects.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.projects.bubbles.components.EventCard
import com.projects.bubbles.components.HeaderBar
import com.projects.bubbles.components.NavigationBar
import com.projects.bubbles.R
import com.projects.bubbles.components.AcessCard
import com.projects.bubbles.components.CommentBox
import com.projects.bubbles.components.ResponseField

@Composable
fun Feed(navController: NavController) {
    var response = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.White)
        ) {
            HeaderBar()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 28.dp, end = 28.dp)
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

                AcessCard()

                CommentBox(
                    userName = "Paulo Alvares",
                    userUsername = "paulinhoAl",
                    postTime = "2 hours ago",
                    commentContent = "Lorem ipsum dolor sit amet consectetur. In dolor porttitor malesuada sit et. Amet enim iaculis gravida nulla egestas ultrices phasellus consequat. Eget mauris in lacus risus porttitor."
                )
            }

            NavigationBar("feed")
        }
    }
}

@Preview
@Composable
fun PreviewFeed() {
    Feed(navController = rememberNavController())
}