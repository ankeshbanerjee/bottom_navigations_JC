package com.example.bottomnavigationjetpackcompose.screens.bottomNav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.bottomnavigationjetpackcompose.Detail
import com.example.bottomnavigationjetpackcompose.LocalRootNavHostController

@Composable
fun HomeTab() {
    val parentNavController = LocalRootNavHostController.current
    HomeTabContent({
        parentNavController.navigate(Detail)
    })
}

@Composable
fun HomeTabContent(goToDetail: () -> Unit){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(text = "Welcome to HomeTab!")
        Button(onClick = goToDetail) {
            Text(text = "Go to Detail")
        }
    }
}

@Preview
@Composable
fun HomeTabPreview() {
    HomeTabContent({})
}