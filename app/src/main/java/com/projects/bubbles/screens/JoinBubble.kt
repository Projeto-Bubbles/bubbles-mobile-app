package com.projects.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.projects.bubbles.components.AcessButton
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
    Spacer(modifier = Modifier.height(70.dp))

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.White)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .width(350.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBubble()
                Spacer(modifier = Modifier.width(10.dp))
                AcessButton(content = "Criar +", onClick = {}, backgroundColor = Color.DarkGray)
            }

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

            Spacer(modifier = Modifier.height(50.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    bubbleCard(
                        "Jogos Vorazes",
                        "Vamos juntos ?",
                        "jogos",
                        painterResource(id = R.mipmap.event_bg),
                        painterResource(id = R.mipmap.games),
                        bubbleYellow
                    )
                }
                item {
                    bubbleCard(
                        "Dia de jogo",
                        "Todo dia resultados de todos os campeonatos mundiais de futebol",
                        "esportes",
                        painterResource(id = R.mipmap.event_bg_2),
                        painterResource(id = R.mipmap.sports),
                        bubbleGreen
                    )
                }
                item {
                    bubbleCard(
                        "Hora do chá",
                        "Todo tipo de comida para alegrar suas tardes",
                        "gastronomia",
                        painterResource(id = R.mipmap.event_bg_3),
                        painterResource(id = R.mipmap.culinary),
                        bubblePurple
                    )
                }
            }
            Spacer(modifier = Modifier.height(70.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    bubbleCard(
                        "Coffee Time",
                        "The new way to programming Java",
                        "tecnologia",
                        painterResource(id = R.mipmap.event_bg_3),
                        painterResource(id = R.mipmap.culinary),
                        bubbleBlue
                    )
                }

                item {
                    bubbleCard(
                        "Salto de Vara com Jaiminho",
                        "Ai Paulinho",
                        "esportes",
                        painterResource(id = R.mipmap.event_bg),
                        painterResource(id = R.mipmap.games),
                        bubbleYellow
                    )
                }
                item {
                    bubbleCard(
                        "RP do paulinho",
                        "Todo dia resultados de todos os campeonatos mundiais de futebol",
                        "jogos",
                        painterResource(id = R.mipmap.event_bg_2),
                        painterResource(id = R.mipmap.sports),
                        bubbleGreen
                    )
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
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
        Bubble("gastronomia", R.mipmap.culinary, bubbleYellow),
        Bubble("livros", R.mipmap.reading, bubbleBlue),
        Bubble("esportes", R.mipmap.sports, bubbleGreen),
        Bubble("arte", R.mipmap.art, bubblePurple),
        Bubble("games", R.mipmap.games, bubbleYellow),
    )
    JoinBubble(lista)
}