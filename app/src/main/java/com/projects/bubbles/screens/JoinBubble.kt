package com.projects.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.projects.bubbles.R
import com.projects.bubbles.components.*
import com.projects.bubbles.model.Bubble
import com.projects.bubbles.ui.theme.*
import com.projects.bubbles.viewmodel.BubbleViewModel

@Composable
fun JoinBubble(bubbleViewModel: BubbleViewModel = viewModel()) {
    val bubbles by bubbleViewModel.bubbleList.observeAsState(emptyList())

    Spacer(modifier = Modifier.height(70.dp))

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .height(400.dp), // Consider adjusting the height as needed
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.White)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.width(350.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBubble()
                Spacer(modifier = Modifier.width(10.dp))
                AcessButton(content = "Criar +", onClick = {}, backgroundColor = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(bubbles.chunked(6)) { rowBubbles ->
                    LazyRow(
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        items(rowBubbles) { bubble ->
                            BubbleCard(
                                title = bubble.title ?: "",
                                description = bubble.explanation ?: "",
                                category = bubble.category?.name ?: "",
                                image = painterResource(id = R.mipmap.forro),
                                icon = painterResource(id = R.mipmap.culinary),
                                color = Color.Red
                            )
                        }
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewJoinBubble() {
    JoinBubble()
}
