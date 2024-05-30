package com.projects.bubbles.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projects.bubbles.R
import com.projects.bubbles.components.ArrowRight
import com.projects.bubbles.components.BubbleLogo
import com.projects.bubbles.components.ButtonSelectCategory
import com.projects.bubbles.components.CategoryButton
import com.projects.bubbles.components.TitleText

@Composable
fun SelectCategory() {
    val backgroundImage: Painter = painterResource(id = R.drawable.selection_bubbles)

    Surface{
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = backgroundImage,
                contentDescription = "Fundo da Tela",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    BubbleLogo()
                }

                Box(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(vertical = 100.dp),
                ) {
                    Column(
                        Modifier.padding(horizontal = 12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(Modifier.padding(start = 20.dp)) {
                            ArrowRight()
                            TitleText(value = stringResource(id = R.string.category_choice, FontWeight.Normal))
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row {
                                CategoryButton(
                                    icon = painterResource(id = R.mipmap.sports),
                                    label = "Esporte",
                                    onClick = {},
                                    backgroundColorButton = Color(0xFFfde68a),
                                    backgroundColorIcon = Color(0xFFede9d9)
                                )
                                CategoryButton(
                                    icon = painterResource(id = R.mipmap.music),
                                    label = "Música",
                                    onClick = {},
                                    backgroundColorButton = Color(0xFFc4b5fd),
                                    backgroundColorIcon = Color(0xFFd4d7fa)
                                )
                                CategoryButton(
                                    icon = painterResource(id = R.mipmap.games),
                                    label = "Games",
                                    onClick = {},
                                    backgroundColorButton = Color(0xFFbbf7d0),
                                    backgroundColorIcon = Color(0xFFe6f2ee)
                                )
                                CategoryButton(
                                    icon = painterResource(id = R.mipmap.art),
                                    label = "Arte",
                                    onClick = {},
                                    backgroundColorButton = Color(0xFFf5d0fe),
                                    backgroundColorIcon = Color(0xFFf0e0ed)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row {
                                CategoryButton(
                                    icon = painterResource(id = R.mipmap.technology),
                                    label = "Tecnologia",
                                    onClick = {},
                                    backgroundColorButton = Color(0xFF99f6e4),
                                    backgroundColorIcon = Color(0xFFdaeff1)
                                )
                                CategoryButton(
                                    icon = painterResource(id = R.mipmap.science),
                                    label = "Ciência",
                                    onClick = {},
                                    backgroundColorButton = Color(0xFFbfdbfe),
                                    backgroundColorIcon = Color(0xFFe0e9f6)
                                )
                                CategoryButton(
                                    icon = painterResource(id = R.mipmap.culinary),
                                    label = "Gastronomia",
                                    onClick = {},
                                    backgroundColorButton = Color(0xFFfed7aa),
                                    backgroundColorIcon = Color(0xFFf2e9dd)
                                )
                                CategoryButton(
                                    icon = painterResource(id = R.mipmap.reading),
                                    label = "Livros",
                                    onClick = {},
                                    backgroundColorButton = Color(0xFFd9f99d),
                                    backgroundColorIcon = Color(0xFFe8f0d7)
                                )
                            }
                        }

                        Spacer(Modifier.height(100.dp))

                        ButtonSelectCategory(
                            value = stringResource(id = R.string.category_selection),
                            onClick = {})
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSelectCategory() {
    SelectCategory()
}