package com.sohail.gittracker.presentation.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sohail.gittracker.presentation.screens.SplashScreen
import com.sohail.gittracker.presentation.navigation.util.Graph
import com.sohail.gittracker.presentation.navigation.util.MainRoutes
import com.sohail.gittracker.presentation.screens.HomeScreen

//Main Navigation Graph that will show all the necessary composables
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun MainNavigationGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController , route = Graph.MAIN, startDestination = MainRoutes.Splash.route){
        composable(route = MainRoutes.Splash.route){
            SplashScreen(navHostController)
        }
        composable(route = MainRoutes.Home.route){
            HomeScreen()
        }
    }
}