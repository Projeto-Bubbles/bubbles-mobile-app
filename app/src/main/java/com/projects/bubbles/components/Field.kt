package com.projects.bubbles.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.projects.bubbles.R
import com.projects.bubbles.ui.theme.Zinc300
import com.projects.bubbles.ui.theme.rounded

@Composable
fun TextFieldT(
    label: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    shape: Shape = rounded.small,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Done,
        keyboardType = androidx.compose.ui.text.input.KeyboardType.Text
    )
) {
    OutlinedTextField(modifier = modifier.fillMaxWidth(),
        shape = shape,
        label = { Text(text = label) },
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        visualTransformation = VisualTransformation.None,
        maxLines = 1,
        leadingIcon = {
            Icon(painter = icon, contentDescription = label)
        })
}



@Composable
fun PasswordField(
    label: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    shape: Shape = rounded.small,
    value: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = PasswordVisualTransformation(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val passwordVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        label = { Text(text = label) },
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Color.Gray,
            focusedBorderColor = Color.Gray,
            focusedLabelColor = Color.Gray,
        ),
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Password),
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(painter = icon, contentDescription = null)
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
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else visualTransformation
    )
}

@Composable
fun ResponseField(onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(
                Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.mipmap.response_arrow),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        BasicTextField(
            value = "",
            onValueChange = onValueChange,
            textStyle = TextStyle.Default.copy(color = Color.Black),
            modifier = Modifier.fillMaxSize(),
        )
    }
}


@Composable
fun SearchBubble() {
    Row(
        modifier = Modifier
            .width(350.dp)
            .height(35.dp)
            .background(
                color = Zinc300,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon",
            modifier = Modifier
                .size(30.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        BasicTextField(
            value = "",
            onValueChange = {},
            textStyle = TextStyle.Default.copy(color = Color.Black),
            modifier = Modifier.fillMaxSize(),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxSize()) {
                    innerTextField()
                    Text(
                        text = "Pesquisar bolhas...",
                        color = Color.Gray,
                        style = TextStyle.Default,
                        modifier = Modifier.padding(horizontal = 2.dp)
                    )
                }
            })
    }
}

//@Composable
//fun SearchBubble(){
//    OutlinedTextField(modifier = modifier.fillMaxWidth(),
//        label = { Text(text = "Pesquisar bolhas...") },
//        value = "",
//        onValueChange = onValueChange,
//        keyboardOptions = keyboardOptions,
//        Icon(
//            imageVector = Icons.Default.Search,
//            contentDescription = "Search Icon",
//            tint = Color.Gray
//        )

