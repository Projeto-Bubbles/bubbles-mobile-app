package com.projects.bubbles.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.bubbles.R
import com.projects.bubbles.dto.CategoryData
import com.projects.bubbles.dto.getCategories
import com.projects.bubbles.ui.theme.Slate800
import com.projects.bubbles.ui.theme.Zinc300


@Composable
fun BubbleCard(
    title: String,
    description: String,
    category: String,
    image: Painter,
) {
    val categories = getCategories()
    val categoryData = categories.find { it.category.name.equals(category, ignoreCase = true) }

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

                BubbleTag(categoryData = categoryData!!, fixed = true, onClick = {})

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
                        color = Color.Black,
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
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = description,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        lineHeight = 16.sp,
                        color = Color.Black
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
                        painter = image,
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

@SuppressLint("DiscouragedApi")
@Composable
fun BubbleTag(
    categoryData: CategoryData,
    isSelected: Boolean? = false,
    fixed: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected!! || fixed) categoryData.color else Zinc300

    val context = LocalContext.current

    val iconResourceId = context.resources.getIdentifier(
        categoryData.iconResourceName,
        "mipmap",
        context.packageName
    )

    Row(
        modifier = Modifier.run {
            background(backgroundColor, RoundedCornerShape(8.dp))
                .clickable(enabled = !fixed, onClick = onClick)
                .padding(horizontal = 12.dp, vertical = 4.dp)
        },
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconResourceId),
            contentDescription = categoryData.title,
            modifier = Modifier.size(16.dp),
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = categoryData.title.lowercase(),
            color = Color(0xFF3f3f46),
            fontWeight = FontWeight.Medium
        )
    }
}

