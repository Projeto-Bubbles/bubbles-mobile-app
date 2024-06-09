package com.projects.bubbles.app

import AuthViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.projects.bubbles.R
import com.projects.bubbles.components.NavbarButton
import com.projects.bubbles.screens.EventScreen
import com.projects.bubbles.screens.Feed
import com.projects.bubbles.screens.JoinBubble

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BubblesApp(
    navController: NavHostController,
    internalNavController: NavHostController,
    authViewModel: AuthViewModel = viewModel(),
    viewModelStoreOwner: ViewModelStoreOwner
) {
    var currentScreen by remember { mutableStateOf("feed") }
    var context = LocalContext.current
    val backgroundImage: Painter = painterResource(id = R.drawable.default_background)

    CompositionLocalProvider(LocalViewModelStoreOwner provides viewModelStoreOwner) {
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

            Column(modifier = Modifier.fillMaxSize()) {
                NavHost(
                    modifier = Modifier.weight(1f),
                    navController = internalNavController,
                    startDestination = "feed"
                ) {
                    composable("feed") {
                        Feed(postViewModel = viewModel(), authViewModel, context)
                    }
                    composable("joinbubbles") {
                        JoinBubble(context = context)
                    }
                    composable("events") {
                        EventScreen(context = context)
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
                            currentScreen = "joinbubbles"
                            internalNavController.navigate("joinbubbles")
                        },
                        isSelected = currentScreen == "joinbubbles"
                    )

                    NavbarButton(
                        icon = painterResource(id = R.drawable.feed_navbar),
                        onClick = {
                            currentScreen = "feed"
                            internalNavController.navigate("feed")
                        },
                        isSelected = currentScreen == "feed"
                    )

                    NavbarButton(
                        icon = painterResource(id = R.drawable.events_navbar),
                        onClick = {
                            currentScreen = "events"
                            internalNavController.navigate("events")
                        },
                        isSelected = currentScreen == "events"
                    )
                }
            }
        }
    }
}

