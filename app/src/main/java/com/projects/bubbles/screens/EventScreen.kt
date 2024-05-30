package com.projects.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projects.bubbles.R
import com.projects.bubbles.components.EventCard
import com.projects.bubbles.ui.theme.Slate100
import com.projects.bubbles.ui.theme.bubbleBlue
import com.projects.bubbles.ui.theme.bubbleGreen
import com.projects.bubbles.ui.theme.bubblePurple

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

        Spacer(modifier = Modifier.height(70.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                EventCard(
                    bolha = "Jogos",
                    titulo = "Torneio de Jogos",
                    descricao = "Um torneio emocionante de jogos",
                    endereco = "Rua dos Jogos, 123",
                    data = "01/05/2024",
                    imagem = painterResource(id = R.mipmap.campeonato),
                    icon = painterResource(id = R.mipmap.games),
                    cor = bubblePurple
                )
            }
            item {
                EventCard(
                    bolha = "Gastronomia",
                    titulo = "Festival de Comida",
                    descricao = "Um festival para os amantes da gastronomia",
                    endereco = "Avenida da Comida, 456",
                    data = "05/05/2024",
                    imagem = painterResource(id = R.mipmap.comida),
                    icon = painterResource(id = R.mipmap.culinary),
                    cor = bubbleGreen
                )
            }
            item {
                EventCard(
                    bolha = "música",
                    titulo = "Vem no Forró",
                    descricao = "Dança e Música Boa",
                    endereco = "R Hadock Lobo, 456",
                    data = "20/05/2024",
                    imagem = painterResource(id = R.mipmap.forro),
                    icon = painterResource(id = R.mipmap.music),
                    cor = bubbleBlue
                )
            }


        }
    }
}
@Preview
@Composable
fun PreviewEventScreen() {
    EventScreen()
}
