package com.sohail.gittracker.ui.theme.presentation.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sohail.gittracker.ui.theme.presentation.screens.SplashScreen
import com.sohail.gittracker.ui.theme.presentation.navigation.util.Graph
import com.sohail.gittracker.ui.theme.presentation.navigation.util.MainRoutes
import com.sohail.gittracker.ui.theme.presentation.screens.HomeScreen

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