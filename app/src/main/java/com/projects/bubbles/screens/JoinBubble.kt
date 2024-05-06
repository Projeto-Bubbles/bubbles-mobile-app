package com.projects.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projects.bubbles.R
import com.projects.bubbles.components.AcessCard
import com.projects.bubbles.components.ButtonSelectBubble
import com.projects.bubbles.components.EventCard
import com.projects.bubbles.components.HeaderBar
import com.projects.bubbles.components.NavigationBar
import com.projects.bubbles.components.ResponseField
import com.projects.bubbles.components.TextField
import com.projects.bubbles.components.bubbleCard
import com.projects.bubbles.model.Bubble
import com.projects.bubbles.ui.theme.Slate100
import com.projects.bubbles.ui.theme.bubbleBlue
import com.projects.bubbles.ui.theme.bubbleGreen
import com.projects.bubbles.ui.theme.bubbleGrey
import com.projects.bubbles.ui.theme.bubblePurple

@Composable
fun JoinBubble(bubbleList: List<Bubble>) {
    Surface(
        modifier = Modifier
            .background(Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.White)
        ) {
            HeaderBar()


            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.weight(1f))
                }
                item {
                    Spacer(modifier = Modifier.weight(1f))
                }

                itemsIndexed(bubbleList) { index, item ->
                    ButtonSelectBubble(
                        valueText = item.nome,
                        icon = painterResource(id = item.icon),
                        onClick = {},
                        backgroundColorButton = corAleatoria(),
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Espaçamento entre os elementos
                }
                item {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    bubbleCard()
                }
                item {
                    bubbleCard()
                }
                item {
                    bubbleCard()
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            bubbleCard()


            NavigationBar("bubbles")
        }
    }
}


@Preview
@Composable
fun PreviewJoinBubble() {
    val lista = listOf(
        Bubble("música", R.mipmap.music, bubbleBlue),
        Bubble("ciência", R.mipmap.science, bubbleGreen),
        Bubble("tecnologia", R.mipmap.technology, bubblePurple),
        Bubble("gastronomia", R.mipmap.culinary, bubbleGreen),
        Bubble("livros", R.mipmap.reading, bubbleGrey)
    )
    JoinBubble(lista)
}