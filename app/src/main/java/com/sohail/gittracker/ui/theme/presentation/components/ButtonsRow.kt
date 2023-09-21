package com.sohail.gittracker.ui.theme.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sohail.gittracker.ui.theme.GitBlack

@Composable
fun ButtonsRow(buttonOne : () -> Unit, buttonTwo : () -> Unit, textOne : String, textTwo : String) {
    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
//        viewModel.showDialog(false)
        Button(
            onClick = buttonOne ,
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = GitBlack
            )
        ) {
            Text(text = textOne, color = Color.White)
        }
        Button(
            onClick = buttonTwo,
//            {
//                viewModel.addRepoToDb(githubRepoEntity = viewModel.state.value.data!!.toGithubRepoEntity())
//            },
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = GitBlack
            )
        ) {
            Text(text = textTwo, color = Color.White)
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}