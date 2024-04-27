package com.projects.bubbles.components

import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.projects.bubbles.R
import com.projects.bubbles.ui.theme.rounded

@Composable
fun TextField(
    label: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    shape: Shape = rounded.small,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        label = { Text(text = label) },
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        leadingIcon = {
            Icon(painter = icon, contentDescription = label)
        }
    )
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