package com.sylviepractices.firebasecompose.ui.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.sylviepractices.firebasecompose.ui.theme.Black
import com.sylviepractices.firebasecompose.ui.theme.SelectedField
import com.sylviepractices.firebasecompose.ui.theme.UnselectedField
import com.sylviepractices.firebasecompose.ui.theme.White

@Composable
fun LoginScreen(auth: FirebaseAuth) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(horizontal = 16.dp),

        ) {
        Icon(
            modifier = Modifier
                .padding(top = 16.dp)
                .size(24.dp),
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "IconBack",
            tint = White
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = "Email",
            color = White,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = "Password",
            color = White,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(modifier = Modifier.size(48.dp))
        Button(modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        //Navegar
                        Log.d("LoginScreen", "Login OK")
                    } else{
                        Log.d("LoginScreen", "Login ERROR")
                    }
                }
            }) {
            Text(text = "Login")
        }
    }
}