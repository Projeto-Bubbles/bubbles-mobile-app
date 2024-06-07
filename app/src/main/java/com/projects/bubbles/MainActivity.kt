package com.projects.bubbles

import AuthViewModel
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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projects.bubbles.app.BubblesApp
import com.projects.bubbles.screens.SignUpScreen
import com.projects.bubbles.ui.theme.BubblesTheme


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BubblesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    SignUpScreen()
                    Tela(navController = rememberNavController())
//                    BubblesApp(navController = rememberNavController())
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun Tela(navController: NavHostController) {
        val context = LocalContext.current
        val authViewModel: AuthViewModel = viewModel()
        val viewModelStoreOwner = LocalViewModelStoreOwner.current
        val internalNavController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "bubbles"
        ) {
            composable("login") {
                SignInScreen(navController, authViewModel, context)
            }
            composable("register") {
                SignUpScreen(navController, authViewModel)
            }
            composable("bubbles") {
                BubblesApp(
                    navController = navController,
                    internalNavController = internalNavController,
                    authViewModel = authViewModel,
                    viewModelStoreOwner = viewModelStoreOwner!! // Corrija o erro de nulidade
                )
            }
        }
    }
}