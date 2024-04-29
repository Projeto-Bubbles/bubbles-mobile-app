package com.projects.bubbles.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.projects.bubbles.R

@Composable
fun HeaderBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        BubbleLogo()


        Perfil()
    }
}

@Composable
fun NavigationBar(currentPage: String) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp),
        contentPadding = PaddingValues(16.dp),
        containerColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavbarIcon(
                currentPage = if (currentPage == "bubbles") true else false,
                icon = painterResource(id = R.mipmap.bubbles_section)
            )
            NavbarIcon(
                currentPage = if (currentPage == "feed") true else false,
                icon = painterResource(id = R.mipmap.feed_section)
            )
            NavbarIcon(
                currentPage = if (currentPage == "events") true else false,
                icon = painterResource(id = R.mipmap.events_section)
            )
        }
    }
}