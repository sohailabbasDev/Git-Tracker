package com.sohail.gittracker.presentation.navigation.util

//Screen routes class
sealed class MainRoutes(val route : String){
    object Splash : MainRoutes("SPLASH_SCREEN")
    object Home : MainRoutes("HOME-SCREEN")
}