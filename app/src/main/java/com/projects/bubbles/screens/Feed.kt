package com.projects.bubbles.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.projects.bubbles.components.EventCard
import com.projects.bubbles.components.HeaderBar
import com.projects.bubbles.components.NavigationBar
import com.projects.bubbles.R

@Composable
fun Feed(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderBar()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 28.dp, end = 28.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                EventCard(image = painterResource(id = R.mipmap.event_bg))
                EventCard(image = painterResource(id = R.mipmap.event_bg_2))
                EventCard(image = painterResource(id = R.mipmap.event_bg_3))
                EventCard(image = painterResource(id = R.mipmap.event_bg_4))
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