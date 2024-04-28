package com.projects.bubbles.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projects.bubbles.R
import com.projects.bubbles.components.NavbarIcon
import com.projects.bubbles.screens.Feed
import com.projects.bubbles.screens.JoinBubble
import com.projects.bubbles.screens.SelectCategory

@Composable
fun BubblesApp(navConroller: NavHostController, modifier: Modifier = Modifier) {
    Column {
        NavHost(
            modifier = modifier,
            navController = navConroller,
            startDestination = "feed"
        ) {
            composable("feed") {
                Feed()
            }
            composable("bubbles") {
                JoinBubble()
            }
            composable("events") {
                SelectCategory()
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(85.dp)
                .padding(16.dp)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(modifier = modifier.weight(1f)) {
                TextButton(onClick = {
                    navConroller.navigate("bubbles")
                }) {
                    NavbarIcon(icon = painterResource(id = R.mipmap.bubbles_section))
                }
            }

            Card(modifier = modifier.weight(1f)) {
                TextButton(onClick = {
                    navConroller.navigate("feed")
                }) {
                    NavbarIcon(icon = painterResource(id = R.mipmap.feed_section))
                }
            }

            Card(modifier = modifier.weight(1f)) {
                TextButton(onClick = {
                    navConroller.navigate("events")
                }) {
                    NavbarIcon(icon = painterResource(id = R.mipmap.events_section))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaPreview() {
    BubblesApp(rememberNavController())
}