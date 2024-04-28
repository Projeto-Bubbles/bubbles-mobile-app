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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
import com.projects.bubbles.components.BubbleLogo
import com.projects.bubbles.components.NavbarButton
import com.projects.bubbles.components.Perfil
import com.projects.bubbles.screens.Feed
import com.projects.bubbles.screens.JoinBubble
import com.projects.bubbles.screens.SelectCategory

@Composable
fun BubblesApp(navConroller: NavHostController, modifier: Modifier = Modifier) {
    var currentScreen by remember { mutableStateOf("feed") }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(start = 32.dp, end = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            BubbleLogo()

            Spacer(modifier = Modifier.weight(1f))

            Perfil()
        }

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
                SelectCategory() // Trocar para a tela de eventos
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(85.dp)
                .padding(16.dp)
                .weight(1f)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavbarButton(
                icon = painterResource(id = R.mipmap.bubbles_section),
                onClick = {
                    currentScreen = "bubbles"
                    navConroller.navigate("bubbles")
                },
                isSelected = currentScreen == "bubbles"
            )

            NavbarButton(
                icon = painterResource(id = R.mipmap.feed_section),
                onClick = {
                    currentScreen = "feed"
                    navConroller.navigate("feed")
                },
                isSelected = currentScreen == "feed"
            )

            NavbarButton(
                icon = painterResource(id = R.mipmap.events_section),
                onClick = {
                    currentScreen = "events"
                    navConroller.navigate("events")
                },
                isSelected = currentScreen == "events"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaPreview() {
    BubblesApp(rememberNavController())
}