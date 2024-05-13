package com.projects.bubbles.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projects.bubbles.R
import com.projects.bubbles.components.NavbarButton
import com.projects.bubbles.components.Perfil
import com.projects.bubbles.screens.Feed
import com.projects.bubbles.screens.JoinBubble

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BubblesApp(navController: NavHostController, modifier: Modifier = Modifier) {
    var currentScreen by remember { mutableStateOf("feed") }
    val backgroundImage: Painter = painterResource(id = R.drawable.default_background)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = "Fundo da Tela",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                NavHost(
                    modifier = modifier,
                    navController = navController,
                    startDestination = "feed"
                ) {
                    composable("feed") {
                        Feed()
                    }
                    composable("bubbles") {
                        JoinBubble()
                    }
                }
            }

            Row(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                NavbarButton(
                    icon = painterResource(id = R.drawable.bubbles_navbar),
                    onClick = {
                        currentScreen = "bubbles"
                        navController.navigate("bubbles")
                    },
                    isSelected = currentScreen == "bubbles"
                )

                NavbarButton(
                    icon = painterResource(id = R.drawable.feed_navbar),
                    onClick = {
                        currentScreen = "feed"
                        navController.navigate("feed")
                    },
                    isSelected = currentScreen == "feed"
                )

                NavbarButton(
                    icon = painterResource(id = R.drawable.events_navbar),
                    onClick = {
                        currentScreen = "events"
                        navController.navigate("events")
                    },
                    isSelected = currentScreen == "events"
                )
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun TelaPreview() {
    BubblesApp(rememberNavController())
}