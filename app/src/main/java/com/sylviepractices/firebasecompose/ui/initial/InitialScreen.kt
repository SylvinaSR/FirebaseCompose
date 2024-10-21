package com.sylviepractices.firebasecompose.ui.initial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sylviepractices.firebasecompose.R
import com.sylviepractices.firebasecompose.ui.theme.BackgroundButton
import com.sylviepractices.firebasecompose.ui.theme.Black
import com.sylviepractices.firebasecompose.ui.theme.Gray
import com.sylviepractices.firebasecompose.ui.theme.Green
import com.sylviepractices.firebasecompose.ui.theme.ShapeButton
import com.sylviepractices.firebasecompose.ui.theme.White

@Preview(showSystemUi = true)
@Composable
fun InitialScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Gray, Black), startY = 0f, endY = 600f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier.clip(CircleShape),
            painter = painterResource(id = R.drawable.spotify),
            contentDescription = "SpotifyIcon"
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Millions of songs.",
            color = White,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Free on Spotify",
            color = White,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 32.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Green)
        ) {
            Text(text = "SingUp Free", color = Black)
        }
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(
            modifier = Modifier.clickable { },
            painter = painterResource(id = R.drawable.google),
            title = "Continue with Google"
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(
            modifier = Modifier.clickable { },
            painter = painterResource(id = R.drawable.facebook),
            title = "Continue with Facebook"
        )
        Text(
            modifier = Modifier.padding(24.dp),
            text = "Log In",
            color = White,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun CustomButton(modifier: Modifier, painter: Painter, title: String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 32.dp)
            .background(BackgroundButton)
            .border(width = 2.dp, color = ShapeButton, shape = CircleShape),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(16.dp),
            painter = painter,
            contentDescription = "IconCustomButton"
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            color = White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
    }
}