package com.projects.bubbles.dto

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.projects.bubbles.R
import com.projects.bubbles.dto.enums.Category
import com.projects.bubbles.ui.theme.Amber200
import com.projects.bubbles.ui.theme.Blue200
import com.projects.bubbles.ui.theme.Fuchsia200
import com.projects.bubbles.ui.theme.Green200
import com.projects.bubbles.ui.theme.Lime200
import com.projects.bubbles.ui.theme.Orange200
import com.projects.bubbles.ui.theme.Teal200
import com.projects.bubbles.ui.theme.Violet300

data class CategoryData(
    val title: String,
    val iconResourceName: String,
    val color: Color,
    val category: Category
)

fun getCategories(): List<CategoryData> {
    return listOf(
        CategoryData("Esportes", "sports", Amber200, Category.SPORTS),
        CategoryData("Música", "music", Violet300, Category.MUSIC),
        CategoryData("Games", "games", Green200, Category.GAME),
        CategoryData("Arte", "art", Fuchsia200, Category.ART),
        CategoryData("Tecnologia", "technology", Teal200, Category.TECHNOLOGY),
        CategoryData("Ciência", "science", Blue200, Category.SCIENCE),
        CategoryData("Gastronomia", "culinary", Orange200, Category.CULINARY),
        CategoryData("Leitura", "reading", Lime200, Category.READING)
    )
}




