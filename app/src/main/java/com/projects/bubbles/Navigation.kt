package com.projects.bubbles

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.projects.bubbles.screens.AccountScreen
import com.projects.bubbles.screens.Feed
import com.projects.bubbles.screens.SelectCategory
import com.projects.bubbles.screens.SignInScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Feed.route) {
        composable(route = Screen.Feed.route) {
            Feed(navController)
        }

        composable(
            route = Screen.Feed.route + "/{name}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
                nullable = true
            })
        ) {
            entry -> AccountScreen(name = entry.arguments?.getString("name"))
        }
    }
}