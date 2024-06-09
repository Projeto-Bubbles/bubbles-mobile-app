package com.projects.bubbles.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.projects.bubbles.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.projects.bubbles.components.EventCard
import com.projects.bubbles.components.EventCardSkeleton
import com.projects.bubbles.dto.EventResponseDTO
import com.projects.bubbles.ui.theme.Slate100
import com.projects.bubbles.utils.DataStoreManager
import com.projects.bubbles.viewmodel.EventViewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import com.projects.bubbles.components.CreateButton
import com.projects.bubbles.components.NotFound
import com.projects.bubbles.components.Search
import com.projects.bubbles.dto.User
import com.projects.bubbles.dto.getCategories
import com.projects.bubbles.utils.AnimationSlider

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventScreen(viewModel: EventViewModel = viewModel(), context: Context) {
    val events by viewModel.eventList.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState()

    var searchText by remember { mutableStateOf("") }
    val categories = getCategories()

    var user by remember { mutableStateOf<User?>(null) }

    LaunchedEffect(Unit) {
        DataStoreManager.getUser(context).collect { fetchedUser ->
            user = fetchedUser
        }
    }

    val filteredEvents = events.filter { event ->
        val categoryMatch = categories.any { categoryData ->
            categoryData.title.contains(
                searchText, ignoreCase = true
            ) && (event.bubble.category == categoryData.category)
        }

        categoryMatch || event.title.contains(searchText, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate100)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.width(350.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Search(placeholder = "Pesquisar eventos...", onValueChange = { searchText = it })

            Spacer(modifier = Modifier.width(10.dp))

            CreateButton(onClick = { /* Lógica para abrir o modal de criação */ })
        }

        Spacer(modifier = Modifier.height(20.dp))

        EventsGrid(events = filteredEvents, isLoading = isLoading ?: true)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventsGrid(events: List<EventResponseDTO>, isLoading: Boolean) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 300.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (isLoading) {
            items(5) {
                EventCardSkeleton()
            }
        } else {
            items(events.size) { index ->
                val event = events[index]

                AnimationSlider {
                    EventCard(event, painterResource(id = R.mipmap.event_bg_2))
                }
            }
        }
    }

    if (!isLoading && events.isEmpty()) {
        NotFound(
            errorMessage = "Esse evento ainda não existe :(",
            suggestion = "Que tal criar um novo evento!?"
        )
    }
}

