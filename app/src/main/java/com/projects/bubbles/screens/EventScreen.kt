package com.projects.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.projects.bubbles.components.EventCard
import com.projects.bubbles.ui.theme.Slate100

@Composable
fun EventScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .background(color = Slate100),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EventCard()
        }
    }
}

@Preview
@Composable
fun PreviewEventScreen() {
    EventScreen()
}
