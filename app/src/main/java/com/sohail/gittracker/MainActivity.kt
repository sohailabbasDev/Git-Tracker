package com.sohail.gittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.rememberNavController
import com.sohail.gittracker.ui.theme.GitTrackerTheme
import com.sohail.gittracker.presentation.navigation.MainNavigationGraph
import dagger.hilt.android.AndroidEntryPoint

//Main activity with navigation graph setup
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //This is our controller
            val navController = rememberNavController()

            GitTrackerTheme {
                //Navigation is setup here
                MainNavigationGraph(navHostController = navController)
            }
        }
    }
}