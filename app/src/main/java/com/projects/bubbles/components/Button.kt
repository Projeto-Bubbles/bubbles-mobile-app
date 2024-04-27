package com.projects.bubbles.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.bubbles.R
import com.projects.bubbles.ui.theme.Slate100
import com.projects.bubbles.ui.theme.Slate800
import com.projects.bubbles.ui.theme.Zinc700

@Composable
fun ButtonComponent(value: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(45.dp),
        contentPadding = PaddingValues(5.dp),
        colors = ButtonDefaults.buttonColors(Zinc700)
    ) {
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Slate100
        )
    }
}

@Composable
fun CategoryButton(
    icon: Painter,
    label: String,
    onClick: () -> Unit,
    backgroundColorButton: Color = Color.LightGray,
    backgroundColorIcon: Color = Color.Gray,
    modifier: Modifier = Modifier
) {
    val clicked = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(horizontal = 4.dp)
            .width(85.dp)
            .height(105.dp)
            .background(
                color = if (clicked.value) backgroundColorButton else Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                clicked.value = !clicked.value
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(color = backgroundColorIcon, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = label,
                tint = Color.Black,
                modifier = Modifier.size(17.dp)
            )
        }
        Text(
            text = label,
            fontSize = 11.sp,
            color = Color(0xFF423f46),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Composable
fun ButtonSelectCategory(value: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .widthIn(240.dp)
            .heightIn(45.dp),
        contentPadding = PaddingValues(5.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFb1c5e1))
    ) {
        Icon(
            painter = painterResource(id = R.mipmap.simple_arrow_rigth),
            contentDescription = "Selecionar Categorias",
            tint = Color(0xFF423f46),
            modifier = Modifier
                .size(30.dp)
                .padding(end = 10.dp)
        )

        Text(
            text = value,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF423f46)
        )
    }
}

@Composable
fun AcessButton(
    content: String,
    onClick: () -> Unit,
    backgroundColor: Color
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(25.dp),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor)
    ) {
        Text(
            text = content,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun ButtonSelectBubble(valueText: String, icon: Painter, buttonColor: Color) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(shape = RoundedCornerShape(6.dp))
            .height(38.dp)
            .background(color = buttonColor)
            .clickable(onClick = {})
    ) {
        Button(
            onClick = {},
            contentPadding = PaddingValues(6.dp),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .height(35.dp),
                    painter = icon,
                    contentDescription = null,
                    tint = Slate800
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = valueText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = Slate800
                )
            }
        }
    }
}