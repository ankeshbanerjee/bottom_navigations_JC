package com.example.bottomnavigationjetpackcompose.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationjetpackcompose.BottomNavigationItems
import com.example.bottomnavigationjetpackcompose.screens.bottomNav.HomeTab
import com.example.bottomnavigationjetpackcompose.screens.bottomNav.ProfileTab
import com.example.bottomnavigationjetpackcompose.screens.bottomNav.SearchTab
import com.example.bottomnavigationjetpackcompose.screens.bottomNav.SettingsTab

@Composable
fun MainScreen() {
    MainScreenContent()
}

val LocalBottomNavHostController = compositionLocalOf<NavHostController> { error("No bottom nav host found!") }

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenContent(){
    val navController = rememberNavController()
    CompositionLocalProvider(LocalBottomNavHostController provides navController) {
        Scaffold (
            bottomBar = {
                BottomNavigationBar()
            }
        ) { innerPadding ->
            NavHost(navController = navController, startDestination = BottomNavigationItems.HomeTabScreen.route){
                composable(BottomNavigationItems.HomeTabScreen.route) {
                    HomeTab()
                }
                composable(BottomNavigationItems.SearchTabScreen.route) {
                    SearchTab()
                }
                composable(BottomNavigationItems.ProfileTabScreen.route) {
                    ProfileTab()
                }
                composable(BottomNavigationItems.SettingsTabScreen.route) {
                    SettingsTab()
                }
            }

        }
    }
}

@Composable
fun BottomNavigationBar() {
    val navController = LocalBottomNavHostController.current
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .shadow(20.dp)
            .border(
                0.5.dp,
                Color.LightGray,
                RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 16.dp)
    ) {

        val navStackBackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navStackBackEntry?.destination

        CustomNavBarItem(
            selected = currentRoute?.hierarchy?.any { it.route == BottomNavigationItems.HomeTabScreen.route } == true,
            onClick = {
                navController.navigate(BottomNavigationItems.HomeTabScreen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = BottomNavigationItems.HomeTabScreen.icon,
            label = BottomNavigationItems.HomeTabScreen.title
        )
        CustomNavBarItem(
            selected = currentRoute?.hierarchy?.any { it.route == BottomNavigationItems.SearchTabScreen.route } == true,
            onClick = {
                navController.navigate(BottomNavigationItems.SearchTabScreen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = BottomNavigationItems.SearchTabScreen.icon,
            label = BottomNavigationItems.SearchTabScreen.title
        )
        Spacer(modifier = Modifier.width(60.dp)) // here size is nearly the size of the FAB
        CustomNavBarItem(
            selected = currentRoute?.hierarchy?.any { it.route == BottomNavigationItems.SettingsTabScreen.route } == true,
            onClick = {
                navController.navigate(BottomNavigationItems.SettingsTabScreen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = BottomNavigationItems.SettingsTabScreen.icon,
            label = BottomNavigationItems.SettingsTabScreen.title
        )
        CustomNavBarItem(
            selected = currentRoute?.hierarchy?.any { it.route == BottomNavigationItems.ProfileTabScreen.route } == true,
            onClick = {
                navController.navigate(BottomNavigationItems.ProfileTabScreen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = BottomNavigationItems.ProfileTabScreen.icon,
            label = BottomNavigationItems.ProfileTabScreen.title
        )
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        val context = LocalContext.current
        LargeFloatingActionButton(
            modifier = Modifier
                .size(64.dp)
                .offset(y = -30.dp),
            onClick = {
                Toast.makeText(context, "FAB Clicked", Toast.LENGTH_SHORT).show()
            },
            shape = CircleShape,
            containerColor = Color.Blue,
        ) {
            Icon(
                Icons.Outlined.Add,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Preview
@Composable
fun CustomNavBarItem(selected: Boolean= true, onClick: () -> Unit = {}, icon: ImageVector = Icons.Outlined.Home, label: String = "Home"){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable{ onClick() }
    ) {
        Icon(icon, tint = if(selected)Color.Blue else Color.Gray, contentDescription = null)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = label, color = if(selected)Color.Blue else Color.Gray)
        Spacer(modifier = Modifier.height(6.dp))
        Box(modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (selected) Color.Blue else Color.Transparent)
            .height(4.dp)
            .width(20.dp)
        )
    }
}

@Preview
@Composable
fun MainScreenPreview(){
    MainScreenContent()
}