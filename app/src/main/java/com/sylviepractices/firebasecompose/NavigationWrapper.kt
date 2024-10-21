package com.sylviepractices.firebasecompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.sylviepractices.firebasecompose.model.NavigationRoutes
import com.sylviepractices.firebasecompose.ui.initial.InitialScreen
import com.sylviepractices.firebasecompose.ui.login.LoginScreen
import com.sylviepractices.firebasecompose.ui.signup.SignUpScreen

@Composable
fun NavigationWrapper(modifier: Modifier, navHostController: NavHostController, auth: FirebaseAuth) {

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = NavigationRoutes.Initial.route
    ) {
        composable(NavigationRoutes.Initial.route) {
            InitialScreen(
                navigateToLogin = { navHostController.navigate(NavigationRoutes.LogIn.route) },
                navigateToSignUp = { navHostController.navigate(NavigationRoutes.SignUp.route) }
            )
        }
        composable(NavigationRoutes.LogIn.route) {
            LoginScreen(
                auth = auth
            )
        }
        composable(NavigationRoutes.SignUp.route) {
            SignUpScreen(
                auth = auth
            )
        }
    }

}