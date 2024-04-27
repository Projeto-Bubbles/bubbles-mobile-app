package com.projects.bubbles

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object SignUp : Screen("sign_up")
    object SignIn : Screen("sign_in")
    object Account : Screen("account")
    object SelectCategory : Screen("select_category")
    object Feed : Screen("feed")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}