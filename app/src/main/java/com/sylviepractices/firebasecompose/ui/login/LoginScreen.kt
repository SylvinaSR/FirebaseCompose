package com.sylviepractices.firebasecompose.ui.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.sylviepractices.firebasecompose.R
import com.sylviepractices.firebasecompose.ui.theme.Black
import com.sylviepractices.firebasecompose.ui.theme.Gray
import com.sylviepractices.firebasecompose.ui.theme.Green
import com.sylviepractices.firebasecompose.ui.theme.SelectedField
import com.sylviepractices.firebasecompose.ui.theme.UnselectedField
import com.sylviepractices.firebasecompose.ui.theme.White

@Composable
fun LoginScreen(viewModel: LoginViewModel) {

    val email: String by viewModel.email.observeAsState(initial = "")

    val password: String by viewModel.password.observeAsState(initial = "")

    val isLoginEnabled by viewModel.isLoginEnabled.observeAsState(initial = false)

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<LoginUiState>(
        initialValue = LoginUiState.Start,
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.uiState.collect {
                value = it
            }
        }
    }

    when (uiState) {
        LoginUiState.Start -> {
            Log.d("LoginScreen", "Start")
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
                MyTitle(text = "Email or UserName")
                MyEmailTextField(email = email) {
                    viewModel.onLoginChanged(email = it, password = password)
                }
                Spacer(modifier = Modifier.size(24.dp))
                MyTitle(text = "Password")
                MyPasswordTextField(password = password) {
                    viewModel.onLoginChanged(email = email, password = it)
                }
                Spacer(modifier = Modifier.size(48.dp))
                Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                    enabled = isLoginEnabled,
                    colors = ButtonDefaults.buttonColors(
                        disabledContainerColor = UnselectedField,
                        disabledContentColor = Black,
                        containerColor = White,
                        contentColor = Black
                    ),
                    onClick = {
                        viewModel.doLogin(email = email, password = password)
                    }) {
                    Text(text = "Login")
                }
            }
        }

        LoginUiState.Loading -> {
            Log.d("LoginScreen", "Loading")
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black)
            ) {
                CircularProgressIndicator(color = Gray, strokeWidth = 5.dp, trackColor = Green)
            }
        }

        LoginUiState.Error -> {
            Log.d("LoginScreen", "Error")
            viewModel.resetUiState()
        }

        LoginUiState.Success -> {
            Log.d("LoginScreen", "Success")
            viewModel.resetUiState()
        }
    }
}

@Composable
fun MyTitle(text: String) {
    Text(
        text = text,
        color = White,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    )
}

@Composable
fun MyEmailTextField(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = email,
        onValueChange = { onTextChanged(it) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = UnselectedField,
            focusedContainerColor = SelectedField
        )
    )
}

@Composable
fun MyPasswordTextField(password: String, onTextChanged: (String) -> Unit) {

    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = password,
        onValueChange = { onTextChanged(it) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = UnselectedField,
            focusedContainerColor = SelectedField
        ),
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
}