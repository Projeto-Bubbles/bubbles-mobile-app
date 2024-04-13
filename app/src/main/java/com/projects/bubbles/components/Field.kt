package com.projects.bubbles.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.bubbles.R
import com.projects.bubbles.ui.theme.Slate100
import com.projects.bubbles.ui.theme.Zinc700
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

//@Composable
//fun CategoryButton(value: String, icon:Painter, onClick: () -> Unit) {
//
//}

