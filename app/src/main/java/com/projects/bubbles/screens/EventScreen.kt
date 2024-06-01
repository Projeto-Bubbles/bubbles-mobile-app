package com.projects.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.projects.bubbles.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.projects.bubbles.components.EventCard
import com.projects.bubbles.ui.theme.Slate100
import com.projects.bubbles.viewmodel.EventViewModel

@Composable
fun EventScreen(viewModel: EventViewModel = viewModel()) {
    val events by viewModel.eventList.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate100)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading!!) {
            CircularProgressIndicator()
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(events.size) { index ->
                    val event = events[index]
                    EventCard(event, painterResource(id = R.mipmap.event_bg_2))
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewEventScreen() {
    EventScreen()
}
