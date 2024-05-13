package com.projects.bubbles.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.bubbles.R
import com.projects.bubbles.ui.theme.Slate100
import com.projects.bubbles.ui.theme.Slate800
import com.projects.bubbles.ui.theme.Zinc700
import com.projects.bubbles.ui.theme.bubblePurple

@Composable
fun ButtonComponent(value: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(45.dp),
        contentPadding = PaddingValues(5.dp),
        colors = ButtonDefaults.buttonColors(Zinc700),
        shape = RoundedCornerShape(8.dp)
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
fun ButtonSelectBubble(
    valueText: String,
    icon: Painter,
    onClick: (() -> Unit)? = null,
    backgroundColorButton: Color,
    modifier: Modifier = Modifier
) {
    val clicked = remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(shape = RoundedCornerShape(6.dp))
            .height(28.dp)
            .background(color = if (clicked.value) backgroundColorButton else Color.LightGray)
            .let {
                if (onClick != null) {
                    it.clickable {
                        clicked.value = !clicked.value
                        onClick()
                    }
                } else {
                    it
                        .background(backgroundColorButton)
                }
            }
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                modifier = Modifier
                    .width(15.dp)
                    .fillMaxHeight(),
                painter = icon,
                contentDescription = null,
                tint = Slate800
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                modifier = Modifier
                    .fillMaxHeight(),
                text = valueText,
                fontSize = 17.sp,
                fontWeight = FontWeight.Light,
                color = Slate800
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
}

@Composable
fun NavbarButton(icon: Painter, onClick: () -> Unit, isSelected: Boolean) {
    val backgroundColor = if (isSelected) Color(0xFFe4e4e4) else Color.Transparent

    TextButton(
        modifier = Modifier
            .height(90.dp)
            .clip(CircleShape)
            .background(backgroundColor),
        onClick = onClick
    ) {
        Icon(
            painter = icon,
            contentDescription = "Bolhas",
            tint = Color(0xFF423f46),
            modifier = Modifier.size(24.dp)
        )
    }
}


@Composable
fun LocalEvent(texto: String) {
    Box(
        modifier = Modifier
            .background(color = Color.LightGray, shape = RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier.padding(3.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = "localizacao",
                modifier = Modifier.size(15.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = texto, style = TextStyle(
                    fontSize = 10.sp,
                )
            )
        }
    }
}

@Composable
fun DataEvent(texto: String) {
    Box(
        modifier = Modifier
            .background(color = Color.LightGray, shape = RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier.padding(3.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.events_navbar),
                contentDescription = "Localização",
                modifier = Modifier.size(15.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = texto, style = TextStyle(
                    fontSize = 10.sp,
                )
            )
        }
    }
}

@Composable
fun HorarioEvent(texto: String) {
    Box(
        modifier = Modifier
            .background(color = Color.LightGray, shape = RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier.padding(3.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "Relógio",
                modifier = Modifier.size(15.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = texto, style = TextStyle(
                    fontSize = 10.sp,
                )
            )
        }
    }
}


@Composable
fun EventButton(
    content: String,
    onClick: () -> Unit,
    backgroundColor: Color
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor)
    ) {
        Text(
            text = content,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun JoinButton(onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(
                shape = RoundedCornerShape(
                    topStart = 120.dp,
                    topEnd = 0.dp,
                    bottomStart = 120.dp,
                    bottomEnd = 0.dp
                )
            )
            .height(25.dp)
            .width(95.dp)
            .background(bubblePurple)
            .clickable { onClick() }
    ) {

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(10.dp))

            Icon(
                painter = painterResource(id = R.mipmap.join),
                contentDescription = "Localização",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxHeight()
                    .width(13.dp),
                tint = Color.Black
            )

            Spacer(modifier = Modifier.width(7.dp))

            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                text = "ENTRAR",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Zinc700,

            )
        }
    }
}


@Composable
fun DeleteButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(30.dp)
            .padding(4.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.feed_navbar),
            contentDescription = "Delete",
            tint = Color.Red // Cor do ícone de lixeira
        )
    }
}
