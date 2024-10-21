package com.sylviepractices.firebasecompose.ui.signup

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.sylviepractices.firebasecompose.R
import com.sylviepractices.firebasecompose.ui.theme.Black
import com.sylviepractices.firebasecompose.ui.theme.SelectedField
import com.sylviepractices.firebasecompose.ui.theme.UnselectedField
import com.sylviepractices.firebasecompose.ui.theme.White

@Composable
fun SignUpScreen(auth: FirebaseAuth) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var isLoginEnabled by rememberSaveable {
        mutableStateOf(false)
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
            onValueChange = {
                email = it
                isLoginEnabled = enableLogin(email = email, password = password)
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
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
            onValueChange = {
                password = it
                isLoginEnabled = enableLogin(email = email, password = password)
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image =
                    if (passwordVisible) R.drawable.ic_hide_password else R.drawable.ic_show_password
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(id = image),
                        contentDescription = "Password Visibility",
                        tint = White
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.size(48.dp))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //Navegar
                        Log.d("SignUpScreen", "Login OK")
                    } else {
                        Log.d("SignUpScreen", "Login ERROR")
                    }
                }
            },
            enabled = isLoginEnabled,
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = UnselectedField,
                containerColor = SelectedField
            )
        ) {
            Text(text = "Login")
        }
    }

}

fun enableLogin(email: String, password: String): Boolean =
    Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6
