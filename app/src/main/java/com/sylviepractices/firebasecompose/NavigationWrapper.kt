package com.sylviepractices.firebasecompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sylviepractices.firebasecompose.ui.initial.InitialScreen
import com.sylviepractices.firebasecompose.ui.login.LoginScreen
import com.sylviepractices.firebasecompose.ui.signup.SignUpScreen

@Composable
fun NavigationWrapper(modifier: Modifier, navHostController: NavHostController) {

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = "Initial"
    ) {
        composable("Initial"){
            InitialScreen()
        }
        composable("LogIn"){
            LoginScreen()
        }
        composable("SignUp"){
            SignUpScreen()
        }
    }

}