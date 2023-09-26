package com.sohail.gittracker.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sohail.gittracker.ui.theme.GitMain
import com.sohail.gittracker.ui.theme.GitRed
import com.sohail.gittracker.ui.theme.GitWhite
import com.sohail.gittracker.domain.model.GithubRepo

//Repository Card That will have a image and two texts which is name and description of a repo
@ExperimentalMaterial3Api
@Composable
fun RepositoryCard(
    repo: GithubRepo,
    dismiss : () -> Unit,
    modifier: Modifier
) {

//    var show by remember { mutableStateOf(true) }
//    val currentItem by rememberUpdatedState(repo)

    val state = rememberDismissState(
        initialValue = DismissValue.Default,
        confirmValueChange = {disValue ->
            if (disValue == DismissValue.DismissedToStart || disValue == DismissValue.DismissedToEnd) {
//                show = false
//                viewModel.deleteRepo(repo)
                dismiss()
                true
            }else false
        },
        positionalThreshold = {float->
            float/2
        }
    )

//    LaunchedEffect(key1 = show ){
//        if (!show) {
//            delay(200)
//            dismiss(currentItem.toGithubRepoEntity())
////            Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show()
//        }
//    }

//    AnimatedVisibility(
//        show, exit = fadeOut(spring())
//    ){
//    }

    SwipeToDismiss(modifier = modifier, state = state, background = {
        Box(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .padding(top = 14.dp)
                .fillMaxSize()
                .background(
                    color = when (state.dismissDirection) {
                        DismissDirection.EndToStart -> {
                            GitRed
                        }

                        else -> {
                            Color.Transparent
                        }
                    }, shape = RoundedCornerShape(14.dp)
                )
        ) {
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.Delete),
                contentDescription = "",
                modifier = Modifier
                    .align(
                        Alignment.CenterEnd
                    )
                    .padding(end = 8.dp)
            )
        }
    }, directions = setOf(DismissDirection.EndToStart), dismissContent = {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .padding(top = 14.dp)
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(
                containerColor = GitMain
            ),
//            .background(GitBlack),
            shape = RoundedCornerShape(14.dp)
        ) {
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .background(GitMain),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .defaultMinSize(minHeight = 42.dp, minWidth = 42.dp)
                        .padding(all = 12.dp)
                        .clip(shape = CircleShape),
                    model = ImageRequest.Builder(LocalContext.current).data(repo.avatarUrl).build(),
                    contentDescription = "image"
                )
                Column(
                    modifier = Modifier
                        .wrapContentSize()
//                verticalArrangement = Arrangement.SpaceEvenly,
//                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        text = repo.name,
                        overflow = TextOverflow.Ellipsis,
//                    softWrap = true,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = GitWhite,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
//                    softWrap = false,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 100,
                        text = repo.description,
                        style = MaterialTheme.typography.bodyMedium.copy(color = GitWhite)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    })
}