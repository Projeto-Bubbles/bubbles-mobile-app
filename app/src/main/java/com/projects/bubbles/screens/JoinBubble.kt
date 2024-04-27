package com.projects.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.projects.bubbles.components.bubbleCard
import com.projects.bubbles.ui.theme.Slate100

@Composable
fun JoinBubble() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .background(color = Slate100)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            bubbleCard()
        }
    }
}

@Preview
@Composable
fun PreviewJoinBubble() {
    JoinBubble()
}