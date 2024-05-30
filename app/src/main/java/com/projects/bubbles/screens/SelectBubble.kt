package com.projects.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projects.bubbles.R
import com.projects.bubbles.components.ArrowRight
import com.projects.bubbles.components.BubbleLogo
import com.projects.bubbles.components.ButtonComponent
import com.projects.bubbles.components.ButtonSelectBubble
import com.projects.bubbles.components.NormalText
import com.projects.bubbles.components.SubtitleText
import com.projects.bubbles.components.TitleText
import com.projects.bubbles.model.Bubble
import com.projects.bubbles.ui.theme.Slate100
import com.projects.bubbles.ui.theme.Zinc300
import com.projects.bubbles.ui.theme.Zinc350
import com.projects.bubbles.ui.theme.bubbleBlue
import com.projects.bubbles.ui.theme.bubbleGreen
import com.projects.bubbles.ui.theme.bubbleGrey
import com.projects.bubbles.ui.theme.bubblePurple
import com.projects.bubbles.ui.theme.bubbleYellow
import com.projects.bubbles.ui.theme.rounded

@Composable
fun SelectBubble(bubbleList: List<Bubble>) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .background(color = Slate100)
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                BubbleLogo()
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(rounded.small)
                    .background(Zinc300)
            ) {
                Column {

                    Column(
                        Modifier.padding(25.dp), verticalArrangement = Arrangement.Center
                    ) {
                        ArrowRight()
                        TitleText(value = stringResource(id = R.string.sign_up_title))
                        NormalText(value = stringResource(id = R.string.sign_up_disclaimer))
                    }

                    Box(
                        Modifier
                            .fillMaxWidth()
                            .clip(rounded.small)
                            .background(Zinc350)
                            .padding(12.dp)
                    ) {
                        Column {


                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(rounded.small)
                                    .background(Zinc350)
                                    .padding(12.dp)
                            ) {
                                Column {
                                    SubtitleText(value = stringResource(id = R.string.bubble_infos))

                                    Spacer(Modifier.height(20.dp))

                                    LazyColumn(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        items(bubbleList.chunked(3)) { rowItems ->
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                horizontalArrangement = Arrangement.spacedBy(-10.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                rowItems.forEach { bubble ->
                                                    ButtonSelectBubble(
                                                        valueText = bubble.nome,
                                                        icon = painterResource(id = bubble.icon),
                                                        onClick = {},
                                                        backgroundColorButton = bubble.cor,
                                                        modifier = Modifier.weight(1f)
                                                    )
                                                }
                                            }
                                            Spacer(modifier = Modifier.height(10.dp))
                                        }
                                    }
                                    Spacer(Modifier.height(20.dp))

                                    ButtonComponent(value = ("Continue -->"),
                                        onClick = {})
                                }



                                }
                            }
                        }

                    }
                }
            }
        }
    }

val cores = listOf(bubbleYellow, bubblePurple, bubbleGreen, bubbleBlue)

@Preview
@Composable
fun PreviewSelectBubble() {
    val lista = listOf(
        Bubble("música", R.mipmap.music, bubbleBlue),
        Bubble("ciência", R.mipmap.science, bubbleGreen),
        Bubble("tecnologia", R.mipmap.technology, bubblePurple),
        Bubble("gastronomia", R.mipmap.culinary, bubbleGreen),
        Bubble("livros", R.mipmap.reading, bubbleGrey),
        Bubble("arte", R.mipmap.art, bubbleGrey),
        Bubble("jogos", R.mipmap.games, bubbleGrey),
        Bubble("esportes", R.mipmap.sports, bubbleGrey),
    )
    SelectBubble(lista)
}
