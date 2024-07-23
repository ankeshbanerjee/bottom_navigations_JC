package com.example.bottomnavigationjetpackcompose.screens

import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
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

@Composable
fun MainScreen(navController: NavHostController) {
    MainScreenContent(navController)
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenContent(parentNavController: NavController){
    val navController = rememberNavController()
    val items = rememberSaveable {
        listOf(
            BottomNavigationItems.HomeTabScreen,
            BottomNavigationItems.SearchTabScreen,
            BottomNavigationItems.ProfileTabScreen
        )
    }
    Scaffold (
        bottomBar = {
            BottomNavigationBar(navController = navController, items = items)
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = BottomNavigationItems.HomeTabScreen.route){
            composable(BottomNavigationItems.HomeTabScreen.route) {
                HomeTab(navController = navController, parentNavController= parentNavController)
            }
            composable(BottomNavigationItems.SearchTabScreen.route) {
                SearchTab(navController = navController,parentNavController= parentNavController)
            }
            composable(BottomNavigationItems.ProfileTabScreen.route) {
                ProfileTab(navController = navController,parentNavController= parentNavController)
            }
        }

    }
}

@Composable
fun BottomNavigationBar(navController: NavController, items: List<BottomNavigationItems>) {
    NavigationBar {
        val navStackBackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navStackBackEntry?.destination

        items.forEach{screen->
            NavigationBarItem(
                selected = currentRoute?.hierarchy?.any{it.route == screen.route} == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = { Icon(screen.icon, contentDescription = null) },
                label = { Text(screen.title) }
            )
        }
    }
}