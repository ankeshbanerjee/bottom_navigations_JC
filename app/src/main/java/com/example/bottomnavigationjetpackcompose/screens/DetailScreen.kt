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
fun DetailScreen() {
    val navController = LocalRootNavHostController.current
    DetailScreenContent(goBack = {
        navController.popBackStack()
    })
}

@Composable
fun DetailScreenContent(goBack: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(text = "Welcome user to details screen!")
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = goBack) {
            Text(text = "Go Back")
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreenContent({})
}