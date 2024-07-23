package com.example.bottomnavigationjetpackcompose.screens

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .shadow(20.dp)
            .border(0.5.dp, Color.LightGray, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 16.dp)
    ) {

        val navStackBackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navStackBackEntry?.destination

        items.forEach{screen->
            CustomNavBarItem(
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
                icon = screen.icon,
                label = screen.title
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
