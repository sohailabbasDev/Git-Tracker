package com.sohail.gittracker.ui.theme.presentation.navigation.util

sealed class MainRoutes(val route : String){
    object Splash : MainRoutes("SPLASH_SCREEN")
    object Home : MainRoutes("HOME-SCREEN")
}