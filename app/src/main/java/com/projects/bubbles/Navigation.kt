package com.projects.bubbles

import androidx.navigation.compose.rememberNavController

fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){

    }
}