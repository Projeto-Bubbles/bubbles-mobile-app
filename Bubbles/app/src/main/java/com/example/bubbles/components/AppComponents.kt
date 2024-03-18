package com.example.bubbles.components

import androidx.compose.foundation.Canvas
import com.example.bubbles.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 15.dp)
            .padding(start = 20.dp),
        style = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ), color = Color.Black,
        textAlign = TextAlign.Start,
    )
}

@Composable
fun SubTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp),
        style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Normal
        ), color = Color.Black,
        textAlign = TextAlign.Start
    )
}

@Composable
fun SubTextComponent2(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Normal
        ), color = Color.Black,
        textAlign = TextAlign.Center
    )
}

@Composable
fun LogoComponent() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.mipmap.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .size(40.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(labelValue: String, icon: ImageVector) {
    val textValue = remember { mutableStateOf("") }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = labelValue) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            value = textValue.value,
            onValueChange = {
                textValue.value = it
            },
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                )
            }
        )
    }

@Composable
fun PasswordTextField(labelValue: String, icon: ImageVector) {

    val passwordState = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Color.Gray,
            focusedBorderColor = Color.Gray,
            focusedLabelColor = Color.Gray,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        value = passwordState.value,
        onValueChange = {
            passwordState.value = it
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { passwordVisible.value = !passwordVisible.value },
                modifier = Modifier.size(50.dp)
            ) {
                val iconImage = if (passwordVisible.value) {
                    Icon(
                        painter = painterResource(id = R.mipmap.visibility),
                        contentDescription = "Senha visível"
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.mipmap.visibility_off),
                        contentDescription = "Senha invisível"
                    )
                }
                iconImage
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun ButtonComponent(value: String) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(45.dp),
        contentPadding = PaddingValues(5.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(45.dp)
                .background(
                    color = Color.DarkGray
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun circle(){
    val imageResId = R.mipmap.circle
    val imagePainter = painterResource(id = imageResId)

    Image(
        painter = imagePainter,
        contentDescription = null,
        modifier = Modifier.size(40.dp)
            .offset(x = 20.dp, y = 16.dp)

    )
}

