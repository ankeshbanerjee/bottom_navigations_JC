package com.example.bottomnavigationjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationjetpackcompose.screens.DetailScreen
import com.example.bottomnavigationjetpackcompose.screens.MainScreen
import com.example.bottomnavigationjetpackcompose.screens.WelcomeScreen
import com.example.bottomnavigationjetpackcompose.ui.theme.BottomNavigationJetpackComposeTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomNavigationJetpackComposeTheme {
                App()
            }
        }
    }
}

@Composable
fun App(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Welcome){
        composable<Welcome> {
            WelcomeScreen(navController)
        }
        composable<Main> {
            MainScreen(navController)
        }
        composable<Detail> {
            DetailScreen(navController)
        }
    }
}

@Serializable
object Welcome

@Serializable
object Main

@Serializable
object Detail