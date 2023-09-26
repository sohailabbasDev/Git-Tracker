package com.sohail.gittracker.presentation.screens.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

//Screen Util helper changes status color and navigation colors
object ScreenUtil {

    @Composable
    fun ChangeStatusAndNavBarColour(color: Color, darkIcons: Boolean, isTopOnly : Boolean = false){
        val systemUiController = rememberSystemUiController()

        if (isTopOnly){
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = color,
                    darkIcons = darkIcons
                )
            }
        }else{
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = color,
                    darkIcons = darkIcons
                )
                systemUiController.setNavigationBarColor(
                    color = color,
                    darkIcons = darkIcons
                )
            }
        }
    }
}