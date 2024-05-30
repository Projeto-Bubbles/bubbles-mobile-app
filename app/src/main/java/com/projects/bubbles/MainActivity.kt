package com.projects.bubbles

import SignInScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projects.bubbles.app.BubblesApp
import com.projects.bubbles.model.Bubble
import com.projects.bubbles.screens.EventScreen
import com.projects.bubbles.screens.Feed
import com.projects.bubbles.screens.JoinBubble
import com.projects.bubbles.screens.SelectBubble
import com.projects.bubbles.screens.SignUpScreen
import com.projects.bubbles.ui.theme.BubblesTheme
import com.projects.bubbles.ui.theme.bubbleBlue
import com.projects.bubbles.ui.theme.bubbleGreen
import com.projects.bubbles.ui.theme.bubblePurple
import com.projects.bubbles.ui.theme.bubbleYellow

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val lista = listOf(
                Bubble("música", R.mipmap.music, bubbleBlue),
                Bubble("ciência", R.mipmap.science, bubbleGreen),
                Bubble("tecnologia", R.mipmap.technology, bubblePurple),
                Bubble("arte", R.mipmap.art, bubblePurple),
                Bubble("livros", R.mipmap.reading, bubbleBlue),
                Bubble("esportes", R.mipmap.sports, bubbleGreen),
                Bubble("gastronomia", R.mipmap.culinary, bubbleYellow),
                Bubble("games", R.mipmap.games, bubbleYellow),
            )
            BubblesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    SignUpScreen()
                    Tela(navController = rememberNavController(), )
//                    BubblesApp(navController = rememberNavController())
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun Tela(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = "login"
        ) {
            composable("login") {
                SignInScreen(navController)
            }
            composable("bubbles") {
                BubblesApp(navController)
            }
        }
    }
}