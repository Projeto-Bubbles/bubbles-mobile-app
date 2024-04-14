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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.projects.bubbles.R
import com.projects.bubbles.Screen
import com.projects.bubbles.components.ArrowRight
import com.projects.bubbles.components.BubbleLogo
import com.projects.bubbles.components.ButtonComponent
import com.projects.bubbles.components.CategoryButton
import com.projects.bubbles.components.NormalText
import com.projects.bubbles.components.PasswordField
import com.projects.bubbles.components.TextField
import com.projects.bubbles.components.TitleText
import com.projects.bubbles.ui.theme.Slate100
import com.projects.bubbles.ui.theme.Zinc300
import com.projects.bubbles.ui.theme.Zinc350
import com.projects.bubbles.ui.theme.rounded
import java.util.Locale.Category

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
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                BubbleLogo()
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Column(
                    Modifier.padding(32.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ArrowRight()
                    TitleText(value = stringResource(id = R.string.category_choice))
                }
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
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    CategoryButton(
                        icon = painterResource(id = R.mipmap.music),
                        label = "Música",
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    CategoryButton(
                        icon = painterResource(id = R.mipmap.games),
                        label = "Games",
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    CategoryButton(
                        icon = painterResource(id = R.mipmap.art),
                        label = "Arte",
                        onClick = {}
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    CategoryButton(
                        icon = painterResource(id = R.mipmap.technology),
                        label = "Tecnologia",
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    CategoryButton(
                        icon = painterResource(id = R.mipmap.science),
                        label = "Ciência",
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    CategoryButton(
                        icon = painterResource(id = R.mipmap.culinary),
                        label = "Gastronomia",
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    CategoryButton(
                        icon = painterResource(id = R.mipmap.reading),
                        label = "Livros",
                        onClick = {}
                    )
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