package com.example.bottomnavigationjetpackcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomNavigationItems (
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object HomeTabScreen: BottomNavigationItems("HOME", "Home", Icons.Outlined.Home)
    object SearchTabScreen: BottomNavigationItems("SEARCH", "Search", Icons.Outlined.Search)
    object ProfileTabScreen: BottomNavigationItems("PROFILE", "Profile", Icons.Outlined.AccountCircle)
    object SettingsTabScreen: BottomNavigationItems("SETTINGS", "Settings", Icons.Outlined.Settings)
}