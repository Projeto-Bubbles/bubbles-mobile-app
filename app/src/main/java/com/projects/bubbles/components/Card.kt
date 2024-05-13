package com.projects.bubbles.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.bubbles.R
import com.projects.bubbles.ui.theme.Slate800
import com.projects.bubbles.ui.theme.Zinc300
import com.projects.bubbles.ui.theme.Zinc500
import com.projects.bubbles.ui.theme.bubbleYellow

@Composable
fun EventStoryCard(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(75.dp)
            .height(115.dp)
            .clip(RoundedCornerShape(50.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun AcessCard() {
    Box(
        modifier = Modifier
            .background(
                Color(0xFF0f172a),
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth()
            .height(120.dp)
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Venha interagir com a bolha!",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.weight(1f))

            Row {
                AcessButton(content = "LOGIN", onClick = {}, backgroundColor = Color(0xFF938acb))
                Spacer(modifier = Modifier.width(16.dp))
                AcessButton(content = "CADASTRO", onClick = {}, backgroundColor = Color(0xFF5676a2))
            }
        }

        Image(
            painter = painterResource(id = R.mipmap.effect),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(60.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun bubbleCard(id: Int, title: String, descricao: String, categoria: String, imagem: Painter) {
    Box(
        modifier = Modifier
            .size(235.dp)
            .background(color = Zinc300, shape = RoundedCornerShape(16.dp))
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonSelectBubble(
                    valueText = categoria,
                    icon = painterResource(id = R.mipmap.games),
                    onClick = null,
                    backgroundColorButton = bubbleYellow,
                )

                Row {
                    Icon(
                        modifier = Modifier
                            .height(16.dp),
                        painter = painterResource(id = R.drawable.user_duotone),
                        contentDescription = null,
                        tint = Slate800
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "22K",
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(horizontal = 15.dp)
            ) {

                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = descricao,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        lineHeight = 16.sp
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(30.dp)
                        )
                ) {
                    Image(
                        painter = imagem,
                        contentDescription = "basquete",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        JoinButton(onClick = { })
                    }
                }
            }
        }
    }
}


@Composable
fun EventCard() {
    Box(
        modifier = Modifier
            .width(320.dp)
            .height(230.dp)
            .background(color = Zinc300, shape = RoundedCornerShape(16.dp))
    ) {
        Column {
            Row {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(120.dp)
                )
                {
                    Image(
                        painter = painterResource(id = R.mipmap.event_bg_2),
                        contentDescription = "basquete",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .clip(
                                shape = RoundedCornerShape(
                                    topStart = 0.dp,
                                    topEnd = 120.dp,
                                    bottomStart = 0.dp,
                                    bottomEnd = 120.dp
                                )
                            ),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {

                    ButtonSelectBubble(
                        valueText = "jogos",
                        icon = painterResource(id = R.mipmap.games),
                        onClick = {},
                        backgroundColorButton = Color(0xFFfde68a)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    TitleTextEvent(value = "Procurando o nemo")
                    SubtitleTextEvent(value = "Aventureiros")

                    Spacer(modifier = Modifier.height(15.dp))

                    LocalEvent(texto = "Rua dos bobos, 15 - SP")
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        DataEvent(texto = "05/12/2023")
                        Spacer(modifier = Modifier.width(10.dp))
                        HorarioEvent(texto = "09h30")
                    }
                    Spacer(modifier = Modifier.height(17.dp))

                    EventButton(
                        content = "MARCAR PRESENÇA",
                        onClick = {},
                        backgroundColor = Zinc500
                    )

                }
            }
        }

    }
}
