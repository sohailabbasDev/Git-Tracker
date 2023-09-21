package com.sohail.gittracker.ui.theme.presentation.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.sohail.gittracker.R
import com.sohail.gittracker.ui.theme.GitBlack
import com.sohail.gittracker.ui.theme.GitWhite

@Composable
fun FloatingButton(onClicked: () -> Unit) {
    FloatingActionButton(onClick = onClicked, containerColor = GitBlack) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_add_24),
            contentDescription = "Add repo",
            tint = GitWhite
        )
    }
}