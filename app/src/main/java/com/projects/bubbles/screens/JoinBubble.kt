package com.projects.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.projects.bubbles.R
import com.projects.bubbles.components.*
import com.projects.bubbles.dto.BubbleResponseDTO
import com.projects.bubbles.dto.getCategories
import com.projects.bubbles.viewmodel.BubbleViewModel

@Composable
fun JoinBubble(bubbleViewModel: BubbleViewModel = viewModel()) {
    val allBubbles by bubbleViewModel.bubbleList.observeAsState(emptyList())

    var searchText by remember { mutableStateOf("") }
    val categories = getCategories()

    val filteredBubbles = allBubbles.filter { bubble ->
        val categoryMatch = categories.any { categoryData ->
            categoryData.title.startsWith(searchText, ignoreCase = true) &&
                    bubble.category == categoryData.category
        }

        categoryMatch || bubble.title?.startsWith(searchText, ignoreCase = true) ?: false
    }

    Spacer(modifier = Modifier.height(70.dp))

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .height(400.dp),
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
                SearchBubble(onValueChange = { searchText = it })

                Spacer(modifier = Modifier.width(10.dp))

                AcessButton(content = "Criar +", onClick = {}, backgroundColor = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(20.dp))

            GridBubbles(bubbles = filteredBubbles ?: allBubbles, bubbleViewModel)
        }
    }
}

@Composable
fun GridBubbles(bubbles: List<BubbleResponseDTO>, viewModel: BubbleViewModel) {
    val chunkedBubbles = bubbles.chunked(6)
    val isLoading = viewModel.isLoading.observeAsState().value

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        if (isLoading == true) {
            items(3) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(4) {
                        BubbleCardSkeleton()
                    }
                }
            }
        } else {
            items(chunkedBubbles) { rowBubbles ->
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(rowBubbles) { bubble ->
                        BubbleCard(
                            title = bubble.title ?: "",
                            description = bubble.explanation ?: "",
                            category = bubble.category?.name ?: "",
                            image = painterResource(id = R.mipmap.forro),
                        )
                    }
                }
            }
        }
    }
}