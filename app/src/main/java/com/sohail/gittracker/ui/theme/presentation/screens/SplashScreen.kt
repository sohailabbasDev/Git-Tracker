package com.sohail.gittracker.ui.theme.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sohail.gittracker.R
import com.sohail.gittracker.ui.theme.GitMain
import com.sohail.gittracker.ui.theme.presentation.navigation.util.MainRoutes
import com.sohail.gittracker.ui.theme.presentation.screens.util.ScreenUtil
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {

    LaunchedEffect(Unit){
        delay(1500)
        navHostController.popBackStack()
        navHostController.navigate(MainRoutes.Home.route)
    }

    ScreenUtil.ChangeStatusAndNavBarColour(color = GitMain, darkIcons = false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .size(140.dp)
                .padding(30.dp),
            painter = painterResource(id = R.drawable.icons8_github2),
            contentDescription = "Git cat logo"
        )
    }
}