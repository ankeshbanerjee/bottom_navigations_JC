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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
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

val LocalRootNavHostController = compositionLocalOf<NavHostController> { error("No root nav host found!") }

@Composable
fun App(){
    val navController = rememberNavController()
    CompositionLocalProvider(LocalRootNavHostController provides navController){
        NavHost(navController = navController, startDestination = Welcome){
            composable<Welcome> {
                WelcomeScreen()
            }
            composable<Main> {
                MainScreen()
            }
            composable<Detail> {
                DetailScreen()
            }
        }
    }
}

@Serializable
object Welcome

@Serializable
object Main

@Serializable
object Detail