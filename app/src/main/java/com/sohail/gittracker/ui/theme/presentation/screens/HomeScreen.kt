package com.sohail.gittracker.ui.theme.presentation.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sohail.gittracker.R
import com.sohail.gittracker.ui.theme.GitBlack
import com.sohail.gittracker.ui.theme.GitGray
import com.sohail.gittracker.ui.theme.GitMain
import com.sohail.gittracker.ui.theme.data.mapper.toGithubRepo
import com.sohail.gittracker.ui.theme.data.mapper.toGithubRepoEntity
import com.sohail.gittracker.ui.theme.presentation.GitRepoViewModel
import com.sohail.gittracker.ui.theme.presentation.components.ButtonsRow
import com.sohail.gittracker.ui.theme.presentation.components.CommonColumn
import com.sohail.gittracker.ui.theme.presentation.components.FloatingButton
import com.sohail.gittracker.ui.theme.presentation.components.RepositoryCard
import com.sohail.gittracker.ui.theme.presentation.screens.util.ScreenUtil
import com.sohail.gittracker.util.Resource
import com.sohail.gittracker.util.Status

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
//    navHostController: NavHostController,
    viewModel: GitRepoViewModel = hiltViewModel(),
    context : Context = LocalContext.current
) {

    ScreenUtil.ChangeStatusAndNavBarColour(color = GitGray, darkIcons = false)
    ScreenUtil.ChangeStatusAndNavBarColour(color = GitMain, darkIcons = false, isTopOnly = true)

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.not_found))

    val state by viewModel.getReposState.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize(),
        containerColor = GitGray,
        floatingActionButton = {
            FloatingButton {
                viewModel.showDialog(!viewModel.isDialogShowing)
                Log.d("tagged", "HomeScreen: ${viewModel.isDialogShowing}")
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { Text(text = "Repositories", style = MaterialTheme.typography.bodyLarge) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = GitMain)
            )
        }
    ) {
        when(viewModel.repoListState.value){
            is Resource.Success -> {
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())){
                    itemsIndexed(items = state, key = { _, item -> item.hashCode()}){_, repo ->
                        RepositoryCard(
                            repo = repo.toGithubRepo(),
                            dismiss = {
                                viewModel.deleteRepo(repo)
                            },
                            modifier = Modifier.animateItemPlacement()
                        )
                    }
                }
            }
            is Resource.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Failed to load please try again")
                }
            }
            is Resource.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = GitBlack)
                }
            }
            is Resource.Initial -> {
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Add a github repository to track")
                }
            }
        }
    }

    if (viewModel.isDialogShowing) {
        Dialog(
            onDismissRequest = { viewModel.isDialogShowing },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(modifier = Modifier.wrapContentSize()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                )
                {
                    when (viewModel.state.value) {
                        is Resource.Initial -> {
                            CommonColumn {
                                Spacer(modifier = Modifier.height(12.dp))
                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(0.9f),
                                    value = viewModel.ownerName.value,
                                    onValueChange = { viewModel.ownerName(it) },
                                    label = { Text(text = "Owner Name", color = Color.White) },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = GitBlack,
                                        unfocusedBorderColor = GitBlack,
                                        selectionColors = TextSelectionColors(
                                            handleColor = GitBlack,
                                            backgroundColor = Color.LightGray
                                        )
                                    )
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(0.9f),
                                    value = viewModel.repoName.value,
                                    onValueChange = { viewModel.repoName(it) },
                                    label = { Text(text = "Repository Name", color = Color.White) },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = GitBlack,
                                        unfocusedBorderColor = GitBlack,
                                        cursorColor = Color.White,
                                        selectionColors = TextSelectionColors(
                                            handleColor = GitBlack,
                                            backgroundColor = Color.LightGray
                                        )
                                    )
                                )
                                ButtonsRow(
                                    buttonOne = { viewModel.showDialog(false) },
                                    buttonTwo = {
                                        if (viewModel.ownerName.value.isNotEmpty() && viewModel.repoName.value.isNotEmpty()) {
                                            viewModel.findRepo(
                                                owner = viewModel.ownerName.value.trim(),
                                                repoName = viewModel.repoName.value.trim()
                                            )
                                        } else {
                                            Toast.makeText(context, "Please enter owner or repo name", Toast.LENGTH_SHORT).show()
                                        }
                                    },
                                    textOne = "Cancel",
                                    textTwo = "Search"
                                )
                            }
                        }

                        is Resource.Loading -> {
                            CommonColumn {
                                CircularProgressIndicator(color = GitBlack)
                            }
                        }

                        is Resource.Success -> {
                            CommonColumn {
                                if (viewModel.state.value.data != null){
                                    when(viewModel.dbState.value){
                                        is Status.Error -> {
                                            Toast.makeText(
                                                context,
                                                "Something went wrong while adding, please try again",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            viewModel.reInit()
                                        }
                                        is Status.Loading -> {
                                            CircularProgressIndicator(color = GitBlack)
                                        }
                                        is Status.Success -> {
                                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                                        }
                                        is Status.Initial -> {
                                            Spacer(modifier = Modifier.height(12.dp))
                                            RepositoryCard(repo = viewModel.state.value.data!!, dismiss = {}, modifier = Modifier)
                                            ButtonsRow(
                                                buttonOne = { viewModel.showDialog(false) },
                                                buttonTwo = {
                                                    viewModel.addRepoToDb(githubRepoEntity = viewModel.state.value.data!!.toGithubRepoEntity())
                                                },
                                                textOne = "Cancel",
                                                textTwo = "Add Repo"
                                            )
                                        }
                                    }
                                }else{
                                    Text(text = "Unable to find object")
                                }
                            }
                        }

                        is Resource.Error -> {
                            CommonColumn{
                                if (viewModel.state.value.message!!.contains("404")) {
                                    LottieAnimation(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(0.3f),
                                        composition = composition,
                                        iterations = LottieConstants.IterateForever,
                                        isPlaying = true
                                    )
                                    Button(
                                        onClick = {
                                            viewModel.reInit()
                                        },
                                        shape = RoundedCornerShape(6.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Black,
                                            contentColor = GitBlack
                                        )
                                    ) {
                                        Text(text = "Retry", color = Color.White)
                                    }
                                } else {
                                    Text(
                                        text = viewModel.state.value.message!!,
                                        textAlign = TextAlign.Center
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Button(
                                        onClick = {
                                            viewModel.reInit()
                                        },
                                        shape = RoundedCornerShape(6.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Black,
                                            contentColor = GitBlack
                                        )
                                    ) {
                                        Text(text = "Retry", color = Color.White)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}