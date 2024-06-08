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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.bubbles.R
import com.projects.bubbles.ui.theme.Red300
import com.projects.bubbles.ui.theme.Slate100
import com.projects.bubbles.ui.theme.Slate800
import com.projects.bubbles.ui.theme.Zinc350
import com.projects.bubbles.ui.theme.Zinc700
import com.projects.bubbles.ui.theme.rounded

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
fun DeleteButton(
    onDelete: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(rounded.small)
            .clickable { onDelete() }
            .background(Red300)
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.icon_delete),
            contentDescription = "Deletar",
            tint = Slate800
        )
    }
}

@Composable
fun EditButton(
    onEdit: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(rounded.small)
            .clickable { onEdit() }
            .background(Zinc350)
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.icon_edit),
            contentDescription = "Editar",
            tint = Slate800
        )
    }
}
