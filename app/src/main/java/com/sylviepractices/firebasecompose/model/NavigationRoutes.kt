package com.sylviepractices.firebasecompose.model

sealed class NavigationRoutes(val route: String) {
    data object Initial: NavigationRoutes("Initial")
    data object LogIn: NavigationRoutes("LogIn")
    data object SignUp: NavigationRoutes("SignUp")
}