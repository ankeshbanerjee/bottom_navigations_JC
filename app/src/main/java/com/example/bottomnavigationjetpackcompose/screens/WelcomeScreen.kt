package com.example.bottomnavigationjetpackcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bottomnavigationjetpackcompose.LocalRootNavHostController
import com.example.bottomnavigationjetpackcompose.Main

@Composable
fun WelcomeScreen() {
    val navController = LocalRootNavHostController.current
    WelcomeScreenContent({
        // navController.navigate(Main)

        // navigation.replace type thing
        navController.navigate(Main){
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    })
}

@Composable
fun WelcomeScreenContent(goToHome: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(text = "Welcome user!")
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = goToHome) {
            Text(text = "Go to Home")
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreenContent({})
}