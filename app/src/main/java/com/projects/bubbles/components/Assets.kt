package com.projects.bubbles.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.projects.bubbles.R

@Composable
fun BubbleLogo() {
    Image(
        modifier = Modifier.fillMaxHeight()
            .size(100.dp),
        painter = painterResource(id = R.mipmap.logo),
        contentDescription = "Logo",
    )
}

@Composable
fun ArrowRight(){
    Image(
        painter = painterResource(id = R.mipmap.arrow_right),
        contentDescription = "Seta para direita",
        modifier = Modifier
            .size(40.dp)
    )
}

@Composable
fun Perfil() {
    Box(
        modifier = Modifier
            .size(25.dp)
            .clip(CircleShape)
            .background(
                color = Color(0xFFe4e4e4),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_user),
            contentDescription = "Perfil",
            tint = Color(0xFF423f46),
            modifier = Modifier.size(13.dp)
        )
    }
}
