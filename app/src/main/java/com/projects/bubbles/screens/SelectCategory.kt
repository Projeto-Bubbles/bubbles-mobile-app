package com.projects.bubbles.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.projects.bubbles.R
import com.projects.bubbles.components.ArrowRight
import com.projects.bubbles.components.BubbleLogo
import com.projects.bubbles.components.CategoryButton
import com.projects.bubbles.components.TitleText
import com.projects.bubbles.ui.theme.Slate100

@Composable
fun SelectCategory(navController: NavController) {
    val context = LocalContext.current

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
                ) {
                    ArrowRight()
                    TitleText(value = stringResource(id = R.string.category_choice))

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
                                backgroundColorButton = Color(0xFFe9e3cf),
                                backgroundColorIcon = Color(0xFFede9d9)
                            )
                            CategoryButton(
                                icon = painterResource(id = R.mipmap.music),
                                label = "Música",
                                onClick = {},
                                backgroundColorButton = Color(0xFFc9cdf9),
                                backgroundColorIcon = Color(0xFFd4d7fa)
                            )
                            CategoryButton(
                                icon = painterResource(id = R.mipmap.games),
                                label = "Games",
                                onClick = {},
                                backgroundColorButton = Color(0xFFe4e4e4),
                                backgroundColorIcon = Color(0xFFe6f2ee)
                            )
                            CategoryButton(
                                icon = painterResource(id = R.mipmap.art),
                                label = "Arte",
                                onClick = {},
                                backgroundColorButton = Color(0xFFe9e3cf),
                                backgroundColorIcon = Color(0xFFede9d9)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row {
                            CategoryButton(
                                icon = painterResource(id = R.mipmap.technology),
                                label = "Tecnologia",
                                onClick = {},
                                backgroundColorButton = Color(0xFFe9e3cf),
                                backgroundColorIcon = Color(0xFFede9d9)
                            )
                            CategoryButton(
                                icon = painterResource(id = R.mipmap.science),
                                label = "Ciência",
                                onClick = {},
                                backgroundColorButton = Color(0xFFe9e3cf),
                                backgroundColorIcon = Color(0xFFede9d9)
                            )
                            CategoryButton(
                                icon = painterResource(id = R.mipmap.culinary),
                                label = "Gastronomia",
                                onClick = {},
                                backgroundColorButton = Color(0xFFe9e3cf),
                                backgroundColorIcon = Color(0xFFede9d9)
                            )
                            CategoryButton(
                                icon = painterResource(id = R.mipmap.reading),
                                label = "Livros",
                                onClick = {},
                                backgroundColorButton = Color(0xFFe9e3cf),
                                backgroundColorIcon = Color(0xFFede9d9)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSelectCategory() {
    SelectCategory(navController = rememberNavController())
}