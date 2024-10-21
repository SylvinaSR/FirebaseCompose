package com.sylviepractices.firebasecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sylviepractices.firebasecompose.ui.theme.FirebaseComposeTheme
import com.sylviepractices.firebasecompose.ui.theme.White

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navHostController = rememberNavController()
            FirebaseComposeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = White
                ) { innerPadding ->
                    NavigationWrapper(
                        modifier = Modifier.padding(innerPadding),
                        navHostController = navHostController
                    )
                }
            }
        }
    }
}
