package com.projects.bubbles.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.projects.bubbles.R

@Composable
fun BubbleLogo() {
    Image(
        painter = painterResource(id = R.mipmap.logo),
        contentDescription = "Logo",
        modifier = Modifier.size(100.dp)
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
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.mipmap.perfil),
            contentDescription = "Perfil",
            tint = Color(0xFF423f46),
            modifier = Modifier.size(13.dp)
        )
    }
}

@Composable
fun NavbarIcon(icon: Painter, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .size(65.dp)
            .clip(CircleShape)
            .background(
                color = Color(0xFFe4e4e4),
                shape = CircleShape
            ),
        onClick = onClick
    ) {
        Icon(
            painter = icon,
            contentDescription = "Bolhas",
            tint = Color(0xFF423f46),
            modifier = Modifier.size(24.dp)
        )
    }
}