package com.projects.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projects.bubbles.R
import com.projects.bubbles.components.ButtonSelectBubble
import com.projects.bubbles.components.SearchBubble
import com.projects.bubbles.components.bubbleCard
import com.projects.bubbles.model.Bubble
import com.projects.bubbles.ui.theme.bubbleBlue
import com.projects.bubbles.ui.theme.bubbleGreen
import com.projects.bubbles.ui.theme.bubblePurple
import com.projects.bubbles.ui.theme.bubbleYellow

@Composable
fun JoinBubble(bubbleList: List<Bubble>) {

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.White)
        ) {

            Spacer(modifier = Modifier.height(20.dp))
            SearchBubble()
            Spacer(modifier = Modifier.height(20.dp))
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
                        backgroundColorButton = item.cor,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
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
                    bubbleCard(1, "Jogos Vorazes", "Vamos juntos ?", "jogos", painterResource(id = R.mipmap.event_bg))
                }
                item {
                    bubbleCard(2,"Dia de jogo", "Todo dia resultados de todos os campeonatos mundiais de futebol", "esportes", painterResource(id = R.mipmap.event_bg_2))
                }
                item {
                    bubbleCard(3,"Hora do chá", "Todo tipo de comida para alegrar suas tardes", "gastronomia", painterResource(id = R.mipmap.event_bg_3))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            bubbleCard(4,"Teste", "Teste Maximum", "teste", painterResource(id = R.mipmap.event_bg_4))


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
        Bubble("livros", R.mipmap.reading, bubbleYellow)
    )
    JoinBubble(lista)
}