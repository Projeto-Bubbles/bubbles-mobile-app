package com.projects.bubbles.components

import android.text.Layout.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.projects.bubbles.R

@Composable
fun BubbleLogo() {
    Image(
        painter = painterResource(id = R.mipmap.logo),
        contentDescription = "Logo",
        modifier = Modifier
            .fillMaxWidth()
            .size(40.dp)
    )
}

@Composable
fun ArrowRight(){
    Image(
        painter = painterResource(id = R.mipmap.arrow_right),
        contentDescription = "Logo",
        modifier = Modifier
            .size(40.dp)
    )
}

