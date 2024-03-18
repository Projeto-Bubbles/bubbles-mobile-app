package com.projects.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.projects.bubbles.ui.theme.Slate100

@Composable
fun Account() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .background(color = Slate100)
    ) {

        Text(text = "Hello name")
    }
}


@Preview
@Composable
fun PreviewAccountScreen() {
    Account()
}